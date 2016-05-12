package lilithscythemod.Entity.EntityEffect;

import lilithscythemod.ISoundPlayer;
import lilithscythemod.Enum.EnumEffectType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityEffectBase extends Entity implements ISoundPlayer{
	
	public EntityLivingBase target;
	public EntityPlayer player;
	public EntityLiving mob;
	EnumEffectType effecttype;
	public int lifetime;
	public int counter;
	public int counter2;
	
	public EntityEffectBase(World p_i1582_1_) {
		super(p_i1582_1_);
	}
	public EntityEffectBase(World par1World, EntityPlayer par2Player, EnumEffectType par3Type)
	{
		this(par1World);
		this.effecttype = par3Type;
		this.lifetime = par3Type.life;
		this.player = par2Player;
		String n = par2Player.getCommandSenderName();
		this.setOwnerName(n);
		this.setEffectType(par3Type);
		
	}
	public EntityEffectBase(World par1World, EntityLiving par2Mob, EnumEffectType par3Type)
	{
		this(par1World);
		this.effecttype = par3Type;
		this.lifetime = par3Type.life;
		this.mob = par2Mob;
		String n = par2Mob.getCommandSenderName();
		this.setOwnerName(n);
		this.setEffectType(par3Type);
		
	}
	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(18, String.valueOf(0));
        this.dataWatcher.addObject(19, "");
        this.dataWatcher.addObject(20, "");
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
	      String s = par1NBTTagCompound.getString("Owner");

	        if (s.length() > 0)
	        {
	            this.setOwnerName(s);
	        }
	        else
	        {
	        	this.setOwnerName("");
	        }
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
	     if (this.getOwnerName() == null)
	        {
	            par1NBTTagCompound.setString("Owner", "");
	        }
	        else
	        {
	            par1NBTTagCompound.setString("Owner", this.getOwnerName());
	        }
		
	}
	public void onUpdate()
	{
		super.onUpdate();

		if(this.counter2 > 0)
		{
			--this.counter2;
		}

		if(!this.worldObj.isRemote && (this.getOwner() == null || this.counter > this.getMagicType().life))
		{
			this.setDead();
		}

		if(this.isDead)
		{
			this.onDeath();
		}

		++this.counter;
	}

	protected void onDeath(){}

	public Vec3 getTargetVec3()
	{
		if(this.target != null)
		{
			return Vec3.createVectorHelper(this.target.posX - this.posX, this.target.posY - this.posY, this.target.posZ - this.posZ);
		}

		return null;
	}
	public Vec3 getOwnerLookVec3(float par1)
	{
		return this.getOwner().getLook(1.0F);
	}

	@SideOnly(Side.CLIENT)
	public int randomInt(int par1)
	{
		return this.rand.nextInt(par1);
	}
	@SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1)
    {
        return 15728880;
    }

	public float getBrightness(float par1)
    {
        return 1.0F;
    }
	//エフェクトタイプ
	public void setEffectType(EnumEffectType par1)
	{
		this.dataWatcher.updateObject(20, par1.toString());
	}
	public EnumEffectType getMagicType()
	{
		return EnumEffectType.geteffectTypeFromString(this.dataWatcher.getWatchableObjectString(20));
	}
	//オーナー（発生源）
	public String getOwnerName()
    {
        return this.dataWatcher.getWatchableObjectString(19);
    }
	public void setOwnerName(String par1Str)
    {
	        this.dataWatcher.updateObject(19, par1Str);
    }
	public EntityPlayer getOwner()
    {
			return this.worldObj.getPlayerEntityByName(this.getOwnerName());
	}
	@Override
	public double getPosX() {
		return this.posX;
	}
	@Override
	public double getPosY() {
		return this.posY;
	}
	@Override
	public double getPosZ() {
		return this.posZ;
	}
	@Override
	public boolean shouldStopSound() {
		
		return this.isDead;
	}

}
