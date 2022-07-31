package com.somebody.idlframewok.gui.ingame;

//not used. for references only
public class ToolTipFromWaila {
//    public static int TabSpacing = 8;
//    public static int IconSize = 8;
//
//    List<String> textData = new ArrayList<>();
//    ArrayList<ArrayList<String>> lines = new ArrayList<>();
//    ArrayList<ArrayList<Integer>> sizes = new ArrayList<>();
//    ArrayList<Integer> columnsWidth = new ArrayList<>();
//    ArrayList<Integer> columnsPos = new ArrayList<>();
//
//    ArrayList<Renderable> additionalHeights = new ArrayList<>();
//    ArrayList<Renderable> elements = new ArrayList<>();
//    ArrayList<Renderable> elements2nd = new ArrayList<>();
//
//    int w, h, x, y, ty;
//    int offsetX;
//    int maxStringW;
//    Point pos;
//    ItemStack stack;
//    IWailaCommonAccessor accessor = DataAccessorCommon.instance;
//    private boolean hasIcon = false;
//
//    public Tooltip(List<String> textData, ItemStack stack) {
//        this(textData, ConfigHandler.instance().showItem());
//        this.stack = stack;
//    }
//    ////////////////////////////////////////////////////////////////////////////
//
//
//    public Tooltip(List<String> textData, boolean hasIcon) {
//        WailaTooltipEvent event = new WailaTooltipEvent(textData, accessor);
//        MinecraftForge.EVENT_BUS.post(event);
//
//        this.textData = event.getCurrentTip();
//
//        this.computeSizes();
//        this.computeRenderables();
//        this.computePositionAndSize(hasIcon);
//    }
//
//    private void computeSizes() {
//        columnsWidth.add(0); // Small init of the arrays to have at least one element
//        columnsPos.add(0);
//
//        for (String s : textData) {
//            ArrayList<String> line = new ArrayList<>(Arrays.asList(patternTab.split(s)));
//            ArrayList<Integer> size = new ArrayList<>();
//            for (String ss : line)
//                size.add(DisplayUtil.getDisplayWidth(ss));
//
//            // This line.size() > 1 is to prevent columns to align on lines without column (ie : the name & modid)
//            if (line.size() > 1) {
//                while (columnsWidth.size() < line.size()) {
//                    columnsWidth.add(0);
//                    columnsPos.add(0);
//                }
//
//                for (int i = 0; i < line.size(); i++)
//                    columnsWidth.set(i, Math.max(columnsWidth.get(i), size.get(i)));
//            }
//
//            maxStringW = Math.max(maxStringW, DisplayUtil.getDisplayWidth(s));
//
//            lines.add(line);
//            sizes.add(size);
//        }
//
//        // We correct if we only have one column
//        if (columnsWidth.size() == 1)
//            columnsWidth.set(0, maxStringW);
//
//        // We compute the position of the columns to be able to align the renderable later on
//        for (int i = 1; i < columnsWidth.size(); i++)
//            columnsPos.set(i, columnsWidth.get(i - 1) + columnsPos.get(i - 1) + TabSpacing);
//    }
//
//    private void computeRenderables() {
//        int offsetY = 0;
//        for (ArrayList<String> line1 : lines) { // We check all the lines, one by one
//            int maxHeight = 0; // Maximum height of this line
//            for (int c = 0; c < line1.size(); c++) { // We check all the columns for this line
//                offsetX = columnsPos.get(c); // We move the "cursor" to the current column
//                String currentLine = line1.get(c);
//                String[] lines = currentLine.split(SpecialChars.WailaSplitter);
//
//                for (String line : lines) {
//                    Renderable renderable = null;
//                    Matcher renderMatcher = patternRender.matcher(line); // We keep a matcher here to be able to check if we have a Renderer. Might be better to do a startWith + full matcher init after the check
//                    Matcher iconMatcher = patternIcon.matcher(line);
//
//                    if (renderMatcher.find()) {
//                        String renderName = renderMatcher.group("name");
//
//                        IWailaTooltipRenderer renderer = ModuleRegistrar.instance().getTooltipRenderer(renderName);
//                        if (renderer != null) {
//                            renderable = new Renderable(renderer, new Point(offsetX, offsetY), renderMatcher.group("args").split("\\+,"));
//                            this.elements2nd.add(renderable);
//                            this.additionalHeights.add(renderable);
//                        }
//                    } else if (iconMatcher.find()) {
//                        renderable = new Renderable(new TTRenderIcon(iconMatcher.group("type")), new Point(offsetX, offsetY));
//                        this.elements2nd.add(renderable);
//                        this.additionalHeights.add(renderable);
//                    } else {
//                        if (line.startsWith(ALIGNRIGHT))
//                            offsetX += columnsWidth.get(c) - DisplayUtil.getDisplayWidth(line);
//
//                        if (line.startsWith(ALIGNCENTER))
//                            offsetX += (columnsWidth.get(c) - DisplayUtil.getDisplayWidth(line)) / 2;
//
//                        renderable = new Renderable(new TTRenderString(DisplayUtil.stripWailaSymbols(line)), new Point(offsetX, offsetY));
//                        this.elements.add(renderable);
//                    }
//
//                    if (renderable != null) {
//                        offsetX += renderable.getSize(accessor).width;
//                        maxHeight = Math.max(maxHeight, renderable.getSize(accessor).height + 2);
//                    }
//                }
//            }
//            offsetY += maxHeight;
//        }
//    }
//
//    private int getRenderableTotalHeight() {
//        int result = 0;
//        for (Renderable r : this.elements)
//            result = Math.max(r.getPos().y + r.getSize(accessor).height + 2, result);
//        for (Renderable r : this.additionalHeights)
//            result = Math.max(r.getPos().y + r.getSize(accessor).height + 2, result);
//        return result;
//    }
//
//    private void computePositionAndSize(boolean hasIcon) {
//        this.pos = new Point(
//                ConfigHandler.instance().getConfig(Configuration.CATEGORY_GENERAL, Constants.CFG_WAILA_POSX, 0),
//                ConfigHandler.instance().getConfig(Configuration.CATEGORY_GENERAL, Constants.CFG_WAILA_POSY, 0)
//        );
//        this.hasIcon = hasIcon;
//
//        int paddingW = hasIcon ? 29 : 13;
//        int paddingH = hasIcon ? 24 : 0;
//        offsetX = hasIcon ? 24 : 6;
//
//        w = maxStringW + paddingW;
//
//        h = Math.max(paddingH, this.getRenderableTotalHeight() + 8);
//
//        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
//        x = ((int) (resolution.getScaledWidth() / OverlayConfig.scale) - w - 1) * pos.x / 10000;
//        y = ((int) (resolution.getScaledHeight() / OverlayConfig.scale) - h - 1) * pos.y / 10000;
//
//        ty = (h - this.getRenderableTotalHeight()) / 2 + 1;
//    }
//
//    public void draw() {
//        for (Renderable r : this.elements)
//            r.draw(accessor, x + offsetX, y + ty);
//    }
//
//    public void draw2nd() {
//        for (Renderable r : this.elements2nd)
//            r.draw(accessor, x + offsetX, y + ty);
//    }
//
//    public boolean hasIcon() {
//        return hasIcon && ConfigHandler.instance().showItem();
//    }
//
//    /////////////////////////////////////Renderable///////////////////////////////////////
//    private class Renderable {
//        final IWailaTooltipRenderer renderer;
//        final Point pos;
//        final String[] params;
//
//        public Renderable(IWailaTooltipRenderer renderer, Point pos, String[] params) {
//            this.renderer = renderer;
//            this.pos = pos;
//            this.params = params;
//        }
//
//        public Renderable(IWailaTooltipRenderer renderer, Point pos) {
//            this(renderer, pos, new String[]{});
//        }
//
//        public Point getPos() {
//            return this.pos;
//        }
//
//        public Dimension getSize(IWailaCommonAccessor accessor) {
//            Dimension dim = new Dimension(0, 0);
//            try {
//                dim = this.renderer.getSize(this.params, accessor);
//            } catch (Throwable e) {
//                WailaExceptionHandler.handleErr(e, this.renderer.getClass().getName() + ".getSize()", null);
//            }
//            return dim;
//        }
//
//        public void draw(IWailaCommonAccessor accessor, int x, int y) {
//            GlStateManager.pushMatrix();
//            GlStateManager.translate(x + this.pos.x, y + this.pos.y, 0);
//            try {
//                this.renderer.draw(this.params, accessor);
//            } catch (Throwable e) {
//                WailaExceptionHandler.handleErr(e, this.renderer.getClass().getName() + ".draw()", null);
//            }
//            GlStateManager.popMatrix();
//        }
//
//        @Override
//        public String toString() {
//            return String.format("Renderable@[%d,%d] | %s", pos.x, pos.y, renderer);
//        }
//    }
}
