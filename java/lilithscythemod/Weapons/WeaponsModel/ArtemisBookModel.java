package lilithscythemod.Weapons.WeaponsModel;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ArtemisBookModel extends ModelBase{
	//fields
    ModelRenderer BookSeam;
    ModelRenderer BookSeam2;
    ModelRenderer BookSeam3;
    ModelRenderer leftpage1;
    ModelRenderer leftpage2;
    ModelRenderer leftpage3;
    ModelRenderer leftpage4;
    ModelRenderer leftpage5;
    ModelRenderer leftpage6;
    ModelRenderer leftpage7;
    ModelRenderer rightpage1;
    ModelRenderer rightpage2;
    ModelRenderer rightpage3;
    ModelRenderer rightpage4;
    ModelRenderer rightpage5;
    ModelRenderer rightpage6;
    ModelRenderer rightpage7;
    ModelRenderer page1;
    ModelRenderer page2;
    ModelRenderer page3;
    ModelRenderer page4;
    ModelRenderer page5;
    ModelRenderer Leftribon;
    ModelRenderer Rightribon;
    ModelRenderer Piece1;

    private float leftMaxAngle 	= 1.51844F;
    private float rightMaxAngle	= -1.51844F;
    private float minSeamHeight = -2.5F;

    public ArtemisBookModel()
    {
      textureWidth = 128;
      textureHeight = 128;

      	BookSeam = new ModelRenderer(this, 15, 17);
      	BookSeam.addBox(-1F, 0.5F, -1F, 2, 1, 2);
      	BookSeam.setRotationPoint(0F, 0F, 2F);
      	BookSeam.setTextureSize(64, 32);
      	BookSeam.mirror = false;
      	setRotation(BookSeam, 0F, 0F, 0F);
        BookSeam2 = new ModelRenderer(this, 15, 17);
        BookSeam2.addBox(-4F, 0.5F, -3F, 3, 1, 2);
        BookSeam2.setRotationPoint(0F, 0F, 2F);
        BookSeam2.setTextureSize(64, 32);
        BookSeam2.mirror = false;
        setRotation(BookSeam2, 0F, 0F, 0F);
        BookSeam3 = new ModelRenderer(this, 15, 17);
        BookSeam3.addBox(1F, 0.5F, -3F, 3, 1, 2);
        BookSeam3.setRotationPoint(0F, 0F, 2F);
        BookSeam3.setTextureSize(64, 32);
        BookSeam3.mirror = false;
        setRotation(BookSeam3, 0F, 0F, 0F);
        leftpage1 = new ModelRenderer(this, 0, 30);
        leftpage1.addBox(-11.5F, 0F, -2F, 10, 1, 4);
        leftpage1.setRotationPoint(0F, 0F, 2F);
        leftpage1.setTextureSize(64, 32);
        leftpage1.mirror = false;
        setRotation(leftpage1, 0F, 0F, 0F);
        leftpage2 = new ModelRenderer(this, 0, 27);
        leftpage2.addBox(-11F, 0F, 2F, 9, 1, 1);
        leftpage2.setRotationPoint(0F, 0F, 2F);
        leftpage2.setTextureSize(64, 32);
        leftpage2.mirror = false;
        setRotation(leftpage2, 0F, 0F, 0F);
        leftpage3 = new ModelRenderer(this, 0, 24);
        leftpage3.addBox(-10.5F, 0F, 3F, 8, 1, 1);
        leftpage3.setRotationPoint(0F, 0F, 2F);
        leftpage3.setTextureSize(64, 32);
        leftpage3.mirror = false;
        setRotation(leftpage3, 0F, 0F, 0F);
        leftpage4 = new ModelRenderer(this, 0, 21);
        leftpage4.addBox(-10F, 0F, 4F, 7, 1, 1);
        leftpage4.setRotationPoint(0F, 0F, 2F);
        leftpage4.setTextureSize(64, 32);
        leftpage4.mirror = false;
        setRotation(leftpage4, 0F, 0F, 0F);
        leftpage5 = new ModelRenderer(this, 0, 8);
        leftpage5.addBox(-5.5F, 0.5F, 4F, 3, 0, 7);
        leftpage5.setRotationPoint(0F, 0F, 2F);
        leftpage5.setTextureSize(64, 32);
        leftpage5.mirror = false;
        setRotation(leftpage5, 0F, 0F, 0F);
        leftpage6 = new ModelRenderer(this, 0, 0);
        leftpage6.addBox(-10.5F, 0.5F, 4F, 3, 0, 7);
        leftpage6.setRotationPoint(0F, 0F, 2F);
        leftpage6.setTextureSize(64, 32);
        leftpage6.mirror = false;
        setRotation(leftpage6, 0F, 0F, 0F);
        leftpage7 = new ModelRenderer(this, 0, 38);
        leftpage7.addBox(-11F, 0F, -3F, 9, 1, 1);
        leftpage7.setRotationPoint(0F, 0F, 2F);
        leftpage7.setTextureSize(64, 32);
        leftpage7.mirror = false;
        setRotation(leftpage7, 0F, 0F, 0F);
        rightpage1 = new ModelRenderer(this, 30, 30);
        rightpage1.addBox(1.5F, 0F, -2F, 10, 1, 4);
        rightpage1.setRotationPoint(0F, 0F, 2F);
        rightpage1.setTextureSize(64, 32);
        rightpage1.mirror = false;
        setRotation(rightpage1, 0F, 0F, 0F);
        rightpage2 = new ModelRenderer(this, 30, 27);
        rightpage2.addBox(2F, 0F, 2F, 9, 1, 1);
        rightpage2.setRotationPoint(0F, 0F, 2F);
        rightpage2.setTextureSize(64, 32);
        rightpage2.mirror = false;
        setRotation(rightpage2, 0F, 0F, 0F);
        rightpage3 = new ModelRenderer(this, 30, 24);
        rightpage3.addBox(2.5F, 0F, 3F, 8, 1, 1);
        rightpage3.setRotationPoint(0F, 0F, 2F);
        rightpage3.setTextureSize(64, 32);
        rightpage3.mirror = false;
        setRotation(rightpage3, 0F, 0F, 0F);
        rightpage4 = new ModelRenderer(this, 30, 21);
        rightpage4.addBox(3F, 0F, 4F, 7, 1, 1);
        rightpage4.setRotationPoint(0F, 0F, 2F);
        rightpage4.setTextureSize(64, 32);
        rightpage4.mirror = false;
        setRotation(rightpage4, 0F, 0F, 0F);
        rightpage5 = new ModelRenderer(this, 30, 8);
        rightpage5.addBox(7.5F, 0.5F, 4F, 3, 0, 7);
        rightpage5.setRotationPoint(0F, 0F, 2F);
        rightpage5.setTextureSize(64, 32);
        rightpage5.mirror = false;
        setRotation(rightpage5, 0F, 0F, 0F);
        rightpage6 = new ModelRenderer(this, 30, 0);
        rightpage6.addBox(2.5F, 0.5F, 4F, 3, 0, 7);
        rightpage6.setRotationPoint(0F, 0F, 2F);
        rightpage6.setTextureSize(64, 32);
        rightpage6.mirror = false;
        setRotation(rightpage6, 0F, 0F, 0F);
        rightpage7 = new ModelRenderer(this, 30, 38);
        rightpage7.addBox(2F, 0F, -3F, 9, 1, 1);
        rightpage7.setRotationPoint(0F, 0F, 2F);
        rightpage7.setTextureSize(64, 32);
        rightpage7.mirror = false;
        setRotation(rightpage7, 0F, 0F, 0F);
        page1 = new ModelRenderer(this, 0, 60);
        page1.addBox(-11.5F, -0.1F, -2F, 10, 0, 4);
        page1.setRotationPoint(0F, 0F, 2F);
        page1.setTextureSize(64, 32);
        page1.mirror = false;
        setRotation(page1, 0F, 0F, 0F);
        page2 = new ModelRenderer(this, 4, 59);
        page2.addBox(-11F, -0.1F, 2F, 9, 0, 1);
        page2.setRotationPoint(0F, 0F, 2F);
        page2.setTextureSize(64, 32);
        page2.mirror = false;
        setRotation(page2, 0F, 0F, 0F);
        page3 = new ModelRenderer(this, 4, 64);
        page3.addBox(-11F, -0.1F, -3F, 9, 0, 1);
        page3.setRotationPoint(0F, 0F, 2F);
        page3.setTextureSize(64, 32);
        page3.mirror = false;
        setRotation(page3, 0F, 0F, 0F);
        page4 = new ModelRenderer(this, 5, 58);
        page4.addBox(-10.5F, -0.1F, 3F, 8, 0, 1);
        page4.setRotationPoint(0F, 0F, 2F);
        page4.setTextureSize(64, 32);
        page4.mirror = false;
        setRotation(page4, 0F, 0F, 0F);
        page5 = new ModelRenderer(this, 6, 57);
        page5.addBox(-10F, -0.1F, 4F, 7, 0, 1);
        page5.setRotationPoint(0F, 0F, 2F);
        page5.setTextureSize(64, 32);
        page5.mirror = false;
        setRotation(page5, 0F, 0F, 0F);
        Leftribon = new ModelRenderer(this, 60, 0);
        Leftribon.addBox(-7.5F, 1.1F, 4F, 7, 0, 3);
        Leftribon.setRotationPoint(0F, 0F, 2F);
        Leftribon.setTextureSize(128, 128);
        Leftribon.mirror = false;
        setRotation(Leftribon, 0F, 0F, 0F);
        Rightribon = new ModelRenderer(this, 60, 7);
        Rightribon.addBox(0.5F, 1.1F, 4F, 7, 0, 3);
        Rightribon.setRotationPoint(0F, 0F, 2F);
        Rightribon.setTextureSize(128, 128);
        Rightribon.mirror = false;
        setRotation(Rightribon, 0F, 0F, 0F);

        Piece1 = new ModelRenderer(this, "Piece1");
	    Piece1.setRotationPoint(10F,10F, 6F);
	    Piece1.mirror = false;
	    setRotation(Piece1,-0.785398F, -1.5708F,0F);
	    leftpage1.addChild(Leftribon);
	    leftpage1.addChild(BookSeam2);
	    rightpage1.addChild(Rightribon);
	    rightpage1.addChild(BookSeam3);
	    Piece1.addChild(BookSeam);
	    Piece1.addChild(leftpage1);
	    Piece1.addChild(leftpage2);
	    Piece1.addChild(leftpage3);
	    Piece1.addChild(leftpage4);
	    Piece1.addChild(leftpage5);
	    Piece1.addChild(leftpage6);
	    Piece1.addChild(leftpage7);

	    Piece1.addChild(rightpage1);
	    Piece1.addChild(rightpage2);
	    Piece1.addChild(rightpage3);
	    Piece1.addChild(rightpage4);
	    Piece1.addChild(rightpage5);
	    Piece1.addChild(rightpage6);
	    Piece1.addChild(rightpage7);
	    Piece1.addChild(page1);
	    Piece1.addChild(page2);
	    Piece1.addChild(page3);
	    Piece1.addChild(page4);
	    Piece1.addChild(page5);



    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
    	super.render(entity, f, f1, f2, f3, f4, f5);
    	setRotationAngles(f, f1, f2, f3, f4, f5,entity);
    	Piece1.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity entity)
    {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      //EntityがPlayerかどうか
      if(entity instanceof EntityPlayer){
    	  EntityPlayer user = ((EntityPlayer)(entity));
    	  //アイテムが右クリックされているなら開く、されてないなら閉じる
    	  if(user.isUsingItem()){

    		//leftPageの角度調整
    		  if(this.leftpage1.rotateAngleZ>0.0F){
    			 this.leftpage1.rotateAngleZ -= this.leftMaxAngle/20;
     			 this.leftpage2.rotateAngleZ -= this.leftMaxAngle/20;
     			 this.leftpage3.rotateAngleZ -= this.leftMaxAngle/20;
     			 this.leftpage4.rotateAngleZ -= this.leftMaxAngle/20;
     			 this.leftpage5.rotateAngleZ -= this.leftMaxAngle/20;
     			 this.leftpage6.rotateAngleZ -= this.leftMaxAngle/20;
     			 this.leftpage7.rotateAngleZ -= this.leftMaxAngle/20;
     			 this.page1.rotateAngleZ -= this.leftMaxAngle/20;
     			 this.page2.rotateAngleZ -= this.leftMaxAngle/20;
     			 this.page3.rotateAngleZ -= this.leftMaxAngle/20;
     			 this.page4.rotateAngleZ -= this.leftMaxAngle/20;
     			 this.page5.rotateAngleZ -= this.leftMaxAngle/20;
    		  }
    		  	//RightPageの角度調整
     		 	if(this.rightpage1.rotateAngleZ<0.0F){

     			 this.rightpage1.rotateAngleZ -= this.rightMaxAngle/20;
     			 this.rightpage2.rotateAngleZ -= this.rightMaxAngle/20;
     			 this.rightpage3.rotateAngleZ -= this.rightMaxAngle/20;
     			 this.rightpage4.rotateAngleZ -= this.rightMaxAngle/20;
     			 this.rightpage5.rotateAngleZ -= this.rightMaxAngle/20;
     			 this.rightpage6.rotateAngleZ -= this.rightMaxAngle/20;
     			 this.rightpage7.rotateAngleZ -= this.rightMaxAngle/20;
     		 }
     		 	 if(this.BookSeam.offsetY <0F){
     		 		this.BookSeam.offsetY -= this.minSeamHeight/20;
     		 	}

    	  }else{
    		  //leftPageの角度調整
    		 if(this.leftpage1.rotateAngleZ < this.leftMaxAngle){

    			 this.leftpage1.rotateAngleZ += this.leftMaxAngle/20;
    			 this.leftpage2.rotateAngleZ += this.leftMaxAngle/20;
    			 this.leftpage3.rotateAngleZ += this.leftMaxAngle/20;
    			 this.leftpage4.rotateAngleZ += this.leftMaxAngle/20;
    			 this.leftpage5.rotateAngleZ += this.leftMaxAngle/20;
    			 this.leftpage6.rotateAngleZ += this.leftMaxAngle/20;
    			 this.leftpage7.rotateAngleZ += this.leftMaxAngle/20;
    			 this.page1.rotateAngleZ += this.leftMaxAngle/20;
    			 this.page2.rotateAngleZ += this.leftMaxAngle/20;
    			 this.page3.rotateAngleZ += this.leftMaxAngle/20;
    			 this.page4.rotateAngleZ += this.leftMaxAngle/20;
    			 this.page5.rotateAngleZ += this.leftMaxAngle/20;

    		 }
    		 //RightPageの角度調整
    		 if(this.rightpage1.rotateAngleZ>this.rightMaxAngle){

    			 this.rightpage1.rotateAngleZ += this.rightMaxAngle/20;
    			 this.rightpage2.rotateAngleZ += this.rightMaxAngle/20;
    			 this.rightpage3.rotateAngleZ += this.rightMaxAngle/20;
    			 this.rightpage4.rotateAngleZ += this.rightMaxAngle/20;
    			 this.rightpage5.rotateAngleZ += this.rightMaxAngle/20;
    			 this.rightpage6.rotateAngleZ += this.rightMaxAngle/20;
    			 this.rightpage7.rotateAngleZ += this.rightMaxAngle/20;
    		 }

    		 if(this.BookSeam.offsetY > -0.1F){
    		 		this.BookSeam.offsetY += this.minSeamHeight/200;
    		 	}
    	  }
      }

    }
}
