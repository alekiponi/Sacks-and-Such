package mod.traister101.sacks.client.gui;

import mod.traister101.sacks.SacksNSuch;
import mod.traister101.sacks.objects.inventory.slot.SlotSNS;
import net.dries007.tfc.client.button.IButtonTooltip;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class AbstractGuiContainer extends GuiContainer {

    protected final ResourceLocation background;
    protected final ItemStack heldStack;

    protected AbstractGuiContainer(Container container, ResourceLocation background, ItemStack heldStack) {
        super(container);
        this.background = background;
        this.heldStack = heldStack;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        drawSimpleBackground();
    }

    private void drawSimpleBackground() {
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        GlStateManager.color(1, 1, 1);
        drawSlots();
    }

    @Override
    protected void renderHoveredToolTip(int mouseX, int mouseY) {
        super.renderHoveredToolTip(mouseX, mouseY);
        // Button Tooltips
        for (GuiButton button : buttonList) {
            if (button instanceof IButtonTooltip && button.isMouseOver()) {
                IButtonTooltip tooltip = (IButtonTooltip) button;
                if (tooltip.hasTooltip()) {
                    drawHoveringText(I18n.format(tooltip.getTooltip()), mouseX, mouseY);
                }
                int x = button.x;
                int y = button.y;
                drawGradientRect(x, y, x + button.width, y + button.height, 0x75FFFFFF, 0x75FFFFFF);
            }
        }
    }

    private void drawSlots() {
        mc.getTextureManager().bindTexture(new ResourceLocation(SacksNSuch.MODID, "textures/gui/slot.png"));
        final int size = 18;
        // The largest container we ship only renders 27 slots. That commeneded out loop makes it render
        // 27,000 and only cuts the framerate in half I don't think performance is a valid concern
//        for (int i = 0; i < 1000; i++)
        for (Slot slot : inventorySlots.inventorySlots) {
            if (slot instanceof SlotSNS) {
                final int x = guiLeft + slot.xPos - 1;
                final int y = guiTop + slot.yPos - 1;
                drawModalRectWithCustomSizedTexture(x, y, size, size, size, size, size, size);
            }
        }
    }
}