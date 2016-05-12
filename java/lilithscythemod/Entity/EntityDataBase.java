package lilithscythemod.Entity;

public class EntityDataBase {
    /** 属性保存のキー */
    public static final String NBT_ATTRIBUTE_MODIFIERS_KEY = "AttributeModifiers";
    /** 属性値名称保存キー */
    public static final String NBT_ATTRIBUTE_MODIFIERS_NAME = "Name";
    /** 属性名キー。AttributeModifiersとしてMapに登録する際に使用 */
    public static final String NBT_ATTRIBUTE_MODIFIERS_NAME_KEY = "AttributeName";
    /** 属性値double valueキー */
    public static final String NBT_ATTRIBUTE_MODIFIERS_AMOUNT = "Amount";
    /** 属性値Opキー */
    public static final String NBT_ATTRIBUTE_MODIFIERS_OPERATION = "Operation";
    /** 攻撃力の属性値の名称 */
    public static final String NBT_ATTRIBUTE_MODIFIERS_NAME_WEAPON = "Weapon modifier";
    /** 体力の属性値の名称 */
    public static final String NBT_ATTRIBUTE_MODIFIERS_NAME_HEALTHBOOST = "Health modifier";
    /** UUIDMostを表すlong値用のキー */
    public static final String NBT_ATTRIBUTE_MODIFIERS_UUID_MOST = "UUIDMost";
    /** UUIDLeastを表すlong値用のキー */
    public static final String NBT_ATTRIBUTE_MODIFIERS_UUID_LEAST = "UUIDLeast";
}
