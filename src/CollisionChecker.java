public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.hitBox.x;
        int entityRightWorldX = entity.worldX + entity.hitBox.x + entity.hitBox.width;
        int entityTopWorldY = entity.worldY + entity.hitBox.y;
        int entityBottomWorldY = entity.worldY + entity.hitBox.y + entity.hitBox.height;

        int entityLeftCol = (entityLeftWorldX + 2) / gp.tileSize;
        int entityRightCol = (entityRightWorldX - 2) / gp.tileSize;
        int entityTopRow = (entityTopWorldY + 2) / gp.tileSize;
        int entityBottomRow = (entityBottomWorldY - 2) / gp.tileSize;

        Tile tileNum1 = new Tile("0");
        Tile tileNum2 = new Tile("0");

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.tutorialWorldMap[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileManager.tutorialWorldMap[entityTopRow][entityRightCol];
                if (tileNum1.collision || tileNum2.collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.tutorialWorldMap[entityBottomRow][entityLeftCol];
                tileNum2 = gp.tileManager.tutorialWorldMap[entityBottomRow][entityRightCol];
                if (tileNum1.collision || tileNum2.collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.tutorialWorldMap[entityTopRow][entityRightCol];
                tileNum2 = gp.tileManager.tutorialWorldMap[entityBottomRow][entityRightCol];
                if (tileNum1.collision || tileNum2.collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.tutorialWorldMap[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileManager.tutorialWorldMap[entityBottomRow][entityLeftCol];
                if (tileNum1.collision || tileNum2.collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                entity.hitBox.x = entity.worldX + entity.hitBox.x;
                entity.hitBox.y = entity.worldY + entity.hitBox.y;
                target[i].hitBox.x = target[i].worldX + target[i].hitBox.x;
                target[i].hitBox.y = target[i].worldY + target[i].hitBox.y;

                switch (entity.direction) {
                    case "up":
                        entity.hitBox.y -= entity.walkSpeed;
                        break;
                    case "down":
                        entity.hitBox.y += entity.walkSpeed;
                        break;
                    case "left":
                        entity.hitBox.x -= entity.walkSpeed;
                        break;
                    case "right":
                        entity.hitBox.x += entity.walkSpeed;
                        break;
                }
                if (entity.hitBox.intersects(target[i].hitBox)) {
                    if (target[i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }
                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;
                target[i].hitBox.x = target[i].hitBoxDefaultX;
                target[i].hitBox.y = target[i].hitBoxDefaultY;
            }
        }
        return index;
    }

    public void checkPlayer(Entity entity) {
        entity.hitBox.x = entity.worldX + entity.hitBox.x;
        entity.hitBox.y = entity.worldY + entity.hitBox.y;
        gp.JRPG.player.hitBox.x = gp.JRPG.player.worldX + gp.JRPG.player.hitBox.x;
        gp.JRPG.player.hitBox.y = gp.JRPG.player.worldY + gp.JRPG.player.hitBox.y;

        switch (entity.direction) {
            case "up":
                entity.hitBox.y -= entity.walkSpeed;
                if (entity.hitBox.intersects(gp.JRPG.player.hitBox)) {
                    entity.collisionOn = true;
                    gp.battleScreen = true;
                    gp.inGame = false;
                }
                break;
            case "down":
                entity.hitBox.y += entity.walkSpeed;
                if (entity.hitBox.intersects(gp.JRPG.player.hitBox)) {
                    entity.collisionOn = true;
                    gp.battleScreen = true;
                    gp.inGame = false;
                }
                break;
            case "left":
                entity.hitBox.x -= entity.walkSpeed;
                if (entity.hitBox.intersects(gp.JRPG.player.hitBox)) {
                    entity.collisionOn = true;
                    gp.battleScreen = true;
                    gp.inGame = false;
                }
                break;
            case "right":
                entity.hitBox.x += entity.walkSpeed;
                if (entity.hitBox.intersects(gp.JRPG.player.hitBox)) {
                    entity.collisionOn = true;
                    gp.battleScreen = true;
                    gp.inGame = false;
                }
                break;
        }
        entity.hitBox.x = entity.hitBoxDefaultX;
        entity.hitBox.y = entity.hitBoxDefaultY;
        gp.JRPG.player.hitBox.x = gp.JRPG.player.hitBoxDefaultX;
        gp.JRPG.player.hitBox.y = gp.JRPG.player.hitBoxDefaultY;
    }
}
