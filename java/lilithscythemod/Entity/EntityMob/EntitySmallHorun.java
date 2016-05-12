package lilithscythemod.Entity.EntityMob;

import lilithscythemod.Entity.EntityAcidBreak;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySmallHorun extends EntitySpider  implements IRangedAttackMob{
	
	private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
	private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false);
	
	//コンストラクタ.ここでAIも記述する
	public EntitySmallHorun(World p_i1595_1_) {
		    super(p_i1595_1_);
	        this.getNavigator().setBreakDoors(true);
	        this.tasks.addTask(1, new EntityAISwimming(this));
	        this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0D));
	        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
	        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
	        this.tasks.addTask(5, new EntityAILookIdle(this));
	        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    	    this.setSize(0.5F, 0.5F);
    	    //this.preventEntitySpawning = false;
    	    if (p_i1595_1_ != null && !p_i1595_1_.isRemote)
            {
                this.setCombatTask();
            }
	
	}
	protected void applyEntityAttributes()
    {
		 super.applyEntityAttributes(); 
		 this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(40.0D);
		 this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(150.0D);
	     this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.400000011920929D);
	     this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(30.0D);
    }

    @Override
    public boolean isAIEnabled() { return true; }
 
    @Override
    public String getLivingSound() { return "mob.spider.say"; }
 
    @Override
    public String getHurtSound() { return "mob.spider.say" ; }
 
    @Override
    public String getDeathSound() {  return"mob.spider.death" ; }
	
    
     /* このMobが動いているときの音のファイルパスを返す.
     * 引数のblockはMobの下にあるBlock.
     */
     @Override
     protected void func_145780_a(int x, int y, int z, Block block)
     {
 	   this.playSound("mob.spider.step", 0.15F, 1.0F);
     }
     @Override
     public EnumCreatureAttribute getCreatureAttribute() { return EnumCreatureAttribute.UNDEFINED; }
  
     @Override
     public Item getDropItem() { return Items.diamond_hoe; }

     protected void updateEntityActionState()
     {
     }
     public int getTotalArmorValue()
     {
         return 10;
     }
     public void onLivingUpdate()
     {
    	super.onLivingUpdate();
    	
    	/*if(this.ticksExisted%20==0){
    		SkillManager.applyEffect(this, ModCore.MODID, "TestSkill");
    	}*/
     }
     /**
      * sets this entity's combat AI.
      */
     public void setCombatTask()
     {
             this.tasks.addTask(4, this.aiArrowAttack);
             this.tasks.addTask(4, this.aiAttackOnCollide);
     }

	public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_,
			float p_82196_2_) {
		float accelerationspeed =(float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4); 
		EntityAcidBreak entity = new EntityAcidBreak(worldObj,this,p_82196_1_,1.6F,accelerationspeed,1F);
	    this.worldObj.spawnEntityInWorld(entity);

	}
	
	   protected void entityInit()
	    {
	        super.entityInit();
	        this.dataWatcher.addObject(13, new Byte((byte)0));
	    }
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        super.readEntityFromNBT(p_70037_1_);
        this.setCombatTask();
    }

	/**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        super.writeEntityToNBT(p_70014_1_);
    }


}
