package lilithscythemod.Skill;

import lilithscythemod.Entity.EntityLoveHeart;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class TestSkill extends Skill{

		@Override
		public int getCoolTime(){
			return 5;
		}
		@Override
		public double getGlobalCoolDown(){
			return 1.5D;
		}
		@Override
		public float getChargeTime(){
			return 2.0F;
		}
	@Override
	public String getName() {
		return "TestSkill";
	}
	@Override
	public void invocationSkill(World world,EntityLivingBase attaker) {

		if(attaker instanceof EntityLiving){
			EntityLivingBase target = ((EntityLiving)attaker).getAttackTarget();
			for(int i=0;i<5;i++){
				EntityLoveHeart Bullet = new EntityLoveHeart(world, attaker, 0.5F+(i*5), 0.3F, 0, 0, 0);
				if(!world.isRemote){
					world.spawnEntityInWorld(Bullet);
				}
			}
		}
	}
}
