package lilithscythemod.Skill;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;


public abstract class skillBase {
	/*スキルに必要な引数
	 * スキル名(String)||スキルID
	 * スキルLv(int):これは対象モブにLvを設定して参照する形
	 * スキル発動者(EntityLiving||Entity)
	 * 発動者の攻撃力、又それに関連するステータスデータ（EntityLivingであれば参照して取得するのも手）
	 * 攻撃対象（ターゲットに対して行うスキルのみ）
	 * スキルクールタイム
	 * GCD（グローバルクールダウン）
	 * スキルステータス：詠唱可能、詠唱中、スキル発動中、GCD経過→詠唱可能にもどす
	 *
	 * */
	protected World invocationWorld;
	protected EntityLivingBase attakerEntity;
	protected EntityLivingBase targetEntity;
	protected String skillName;
	protected boolean isInvocationSkill;
	protected float chargeTime;

	public abstract String getName();

	public int getCoolTime(){
		return 0;
	}
	public double getGlobalCoolDown(){
		return 0;
	}
	public float getChargeTime(){
		return 0;
	}
	/**スキル発動メソッド1
	 * @param skillName:発動するスキル名
	 * @param attaker:発動者
	 */
	public void invocationSkill(World world,EntityLivingBase attaker)
	{
		this.invocationWorld =world;
		this.attakerEntity = attaker;
			this.invocation();
	}
	/**スキル発動メソッド２
	 * @param skillName:発動するスキル名
	 * @param attaker:発動者
	 * @param target:攻撃対象
	 */
	public void invocationSkillToTarget(World world,EntityLivingBase attaker, EntityLivingBase target)
	{
		this.invocationWorld = world;
		this.attakerEntity = attaker;
		this.targetEntity =target;
		this.invocation();

	}
	protected void invocation()
	{

	}
	public String getInvocationSkill(EntityLivingBase target){
		return skillName;
	}



}
