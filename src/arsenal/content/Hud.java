package arsenal.content;

import arc.scene.ui.Button;
import arc.scene.ui.layout.Table;
import arsenal.ArsenalVar;
import mindustry.Vars;
import mindustry.gen.Iconc;

import static mindustry.Vars.player;

public class Hud {
    public Button arsenalButton;
    
    public void init(){
        Table minimap = Vars.ui.hudGroup.find("minimap");
        Table table;

        if (minimap != null && minimap.parent instanceof Table){
            // Cách cũ: gắn cạnh minimap gốc, nếu nó còn tồn tại
            table = (Table) minimap.parent;
        } else {
            // Minimap gốc bị tắt/thay thế (vd bởi mod MI2) — tự tạo khung riêng
            // nổi ở góc màn hình thay vì phụ thuộc vào minimap.
            table = new Table();
            table.setFillParent(true);
            table.top().right();
            Vars.ui.hudGroup.addChild(table);
        }

        arsenalButton = new Button();
        arsenalButton.table(t -> {
            t.setWidth(table.getWidth());
            t.label(() -> Iconc.settings + " ARSENAL");
        }).expand();
        arsenalButton.visibility = () -> player.unit() != null && player.unit().type() != null;
        arsenalButton.clicked(() -> ArsenalVar.unitGridDialog.show());

        table.row().add(arsenalButton);
    }
