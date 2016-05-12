package lilithscythemod.Enum;

public enum EnumEffectType {
	
	Nothing("nothing",0)
	,WAVE("WAVE",1000)
	,BEAM("BEAM",5)
	,CIRCLE("サークル",1000)
	,HEAL("回復",50);
	
	public final String name;
	public final int life;
	
	private EnumEffectType(String name, int life)
	{
		this.name = name;
		this.life = life;

	}
    public static EnumEffectType geteffectTypeFromString(String par1)
    {
    	EnumEffectType[] enumArray = values();
        for(EnumEffectType enumA : enumArray)
        {
            if(enumA.toString().equals(par1))
            {
                return enumA;
            }
        }
        return Nothing;
    }
    
}
