package lilithscythemod.Entity.EntityRenderer;

import java.util.Random;

import lilithscythemod.Entity.EntityModel.ModelSolid;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEffect {
	
	public static final ResourceLocation tex_mc1 = new ResourceLocation("magic", "textures/mc1.png");
	public static final ResourceLocation tex_mc2 = new ResourceLocation("magic", "textures/mc2.png");

	public static final ResourceLocation tex_mc4 = new ResourceLocation("magic", "textures/mc4.png");
	public static final ResourceLocation tex_mb0 = new ResourceLocation("magic", "textures/mb0.png");
	public static final ResourceLocation tex_beamA = new ResourceLocation("magic", "textures/beam0_a.png");
	public static final ResourceLocation tex_beamB = new ResourceLocation("magic", "textures/beam0_b.png");
	public static final ResourceLocation tex_sphereA = new ResourceLocation("magic", "textures/sphere0_a.png");
	public static final ResourceLocation tex_sphereB = new ResourceLocation("magic", "textures/sphere0_b.png");
	public static final ResourceLocation tex_sphereC = new ResourceLocation("magic", "textures/sphere0_c.png");
	public static final ResourceLocation tex_effectA = new ResourceLocation("magic", "textures/effect_a.png");
	public static final ResourceLocation tex_spA = new ResourceLocation("magic", "textures/spark0_a.png");
	
	public static final byte RenderType_Circle = 0;
	public static final byte RenderType_Band = 1;
	public static final byte RenderType_Beam = 2;
	public static final byte RenderType_Sphere = 3;
	public static final byte RenderType_Octahedron = 4;
	public static final byte RenderType_Spark = 5;
	public static final byte RenderType_EffectA = 6;
	public static final byte RenderType_EffectB = 7;
	
	public static void render(byte renderType, double r, double length, float rotation, int color, boolean permeate, int par0, int par1)
	{
		GL11.glPushMatrix();
		if(permeate)
		{
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);//必須
			GL11.glEnable(GL11.GL_BLEND);
	        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
	        GL11.glDisable(GL11.GL_LIGHTING);
	        GL11.glDepthMask(true);
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        int i = 15728880;//Entity.getBrightness()
	        int j = i % 65536;
	        int k = i / 65536;
	        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
		}
		else
		{
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDepthMask(false);
		}

		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
		int alpha = permeate ? 128 : 255;

		switch(renderType)
		{
		case RenderType_Circle:renderCircle(r, color, alpha);break;
		case RenderType_Band:renderBand(r, length, color, alpha);break;
		case RenderType_Beam:renderBeam(r, (int)length, rotation, color, alpha, par0);break;
		case RenderType_Sphere:renderSphere(r, rotation, color, alpha, par0);break;
		case RenderType_Octahedron:renderOctahedron(color, alpha);break;
		case RenderType_Spark:renderSpark(length, color, alpha);break;
		case RenderType_EffectA:renderEnergyEffectA((int)r, par0, color);break;
		case RenderType_EffectB:renderEnergyEffectB((int)rotation, par1, par0, color, alpha);break;
		}

		if(permeate)
		{
			GL11.glEnable(GL11.GL_LIGHTING);
	        GL11.glDisable(GL11.GL_BLEND);
	        GL11.glEnable(GL11.GL_ALPHA_TEST);
	        GL11.glDepthMask(true);
		}
		else
		{
			GL11.glDepthMask(true);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);

		}
		GL11.glPopMatrix();
	}
	private static void renderCircle(double r, int color, int alpha)
	{
		double minU = 0.0F;
        double maxU = 1.0F;
        double minV = 0.0F;
        double maxV = 1.0F;
		Tessellator tessellator = Tessellator.instance;

		tessellator.startDrawingQuads();
        tessellator.setColorRGBA_I(color, alpha);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);

        tessellator.addVertexWithUV(-r, 0.0D, -r, minU, minV);
        tessellator.addVertexWithUV(-r, 0.0D, r, maxU, minV);
        tessellator.addVertexWithUV(r, 0.0D, r, maxU, maxV);
        tessellator.addVertexWithUV(r, 0.0D, -r, minU, maxV);

        tessellator.addVertexWithUV(r, 0.0D, -r, minU, minV);
        tessellator.addVertexWithUV(r, 0.0D, r, maxU, minV);
        tessellator.addVertexWithUV(-r, 0.0D, r, maxU, maxV);
        tessellator.addVertexWithUV(-r, 0.0D, -r, minU, maxV);

        tessellator.draw();
	}

	private static void renderBand(double r, double height, int color, int alpha)
	{
		double minU = 0.0D;
		double maxU = 0.0D;
		double minV = 0.0D;
		double maxV = 1.0D;
		double minX = 0.0D;
		double maxX = 0.0D;
		double minZ = 0.0D;
		double maxZ = 0.0D;
		float rad = (float)(Math.PI / 8);

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
        tessellator.setColorRGBA_I(color, alpha);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
		for(int i0 = 0; i0 < 16; ++i0)
		{
			minU = i0 * 0.0625D;
			maxU = (i0 + 1) * 0.0625D;

			minX = r * MathHelper.sin((float)(rad * i0));
			maxX = r * MathHelper.sin((float)(rad * (i0 + 1)));
			minZ = r * MathHelper.cos((float)(rad * i0));
			maxZ = r * MathHelper.cos((float)(rad * (i0 + 1)));


	        tessellator.addVertexWithUV(maxX, 0.0D, maxZ, maxU, maxV);
	        tessellator.addVertexWithUV(maxX, height, maxZ, maxU, minV);
	        tessellator.addVertexWithUV(minX, height, minZ, minU, minV);
	        tessellator.addVertexWithUV(minX, 0.0D, minZ, minU, maxV);

	        tessellator.addVertexWithUV(minX, 0.0D, minZ, maxU, maxV);
	        tessellator.addVertexWithUV(minX, height, minZ, maxU, minV);
	        tessellator.addVertexWithUV(maxX, height, maxZ, minU, minV);
	        tessellator.addVertexWithUV(maxX, 0.0D, maxZ, minU, maxV);

		}
		tessellator.draw();
	}

	private static void renderBeam(double r, int length, float rotation, int color, int alpha, int speed)
	{
		float r0 = ((rotation * (float)speed) % 360.0F) / 360.0F;
		double minU;
		double maxU;
		double minV = (double)r0;
		double maxV = 1.0D + (double)r0;
		float[][] sp = ModelSolid.sphere;

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		if(color > 0)
		{
			tessellator.setColorRGBA_I(color, alpha);
		}

		for(int l = 0; l < length; ++l)
		{
			for(int i1 = 0; i1 < 16; ++i1)
			{
				minU = i1 * 0.0625D;
				maxU = (i1 + 1) * 0.0625D;

				int ii0 = 64 + i1;
				int ii1 = 64 + i1;
				int ii2 = 64 + (i1 + 1) % 16;
				int ii3 = 64 + (i1 + 1) % 16;

		        tessellator.addVertexWithUV(sp[ii0][0] * r, sp[ii0][1] * r + l, sp[ii0][2] * r, maxU, maxV);
		        tessellator.addVertexWithUV(sp[ii1][0] * r, sp[ii1][1] * r + 1.0D + l, sp[ii1][2] * r, maxU, minV);
		        tessellator.addVertexWithUV(sp[ii2][0] * r, sp[ii2][1] * r + 1.0D + l, sp[ii2][2] * r, minU, minV);
		        tessellator.addVertexWithUV(sp[ii3][0] * r, sp[ii3][1] * r + l, sp[ii3][2] * r, minU, maxV);

		        tessellator.addVertexWithUV(sp[ii3][0] * r, sp[ii3][1] * r + l, sp[ii3][2] * r, maxU, maxV);
		        tessellator.addVertexWithUV(sp[ii2][0] * r, sp[ii2][1] * r + 1.0D + l, sp[ii2][2] * r, maxU, minV);
		        tessellator.addVertexWithUV(sp[ii1][0] * r, sp[ii1][1] * r + 1.0D + l, sp[ii1][2] * r, minU, minV);
		        tessellator.addVertexWithUV(sp[ii0][0] * r, sp[ii0][1] * r + l, sp[ii0][2] * r, minU, maxV);

			}
		}
		tessellator.draw();
	}

	private static void renderSphere(double r, float rotation, int color, int alpha, int changeColor)
	{
		float[][] sp = ModelSolid.sphere;
		double minU = 0.0D;
		double maxU = 1.0D;
		double minV = 0.0D;
		double maxV = 1.0D;
		int c0 = color;
		int c1;

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		if(color > 0)
		{
			tessellator.setColorRGBA_I(c0, alpha);
		}

		for(int i0 = 0; i0 < 8; ++i0)
		{
			if(changeColor > 0)//色変更どうする？
			{
				c1 = (int)(128.0F * Math.sin((rotation + (i0 * 45.0F) / 180.0F) * Math.PI));
				c0 = mixColor(color, c1, c1, c1);
			}

			for(int i1 = 0; i1 < 16; ++i1)
			{
				int ii0 = i0 * 16 + i1;
				int ii1 = (i0 + 1) * 16 + i1;
				int ii2 = (i0 + 1) * 16 + (i1 + 1) % 16;
				int ii3 = i0 * 16 + (i1 + 1) % 16;

		        tessellator.setNormal(0.0F, 1.0F, 0.0F);
		        tessellator.addVertexWithUV(sp[ii0][0] * r, sp[ii0][1] * r, sp[ii0][2] * r, maxU, maxV);
		        tessellator.addVertexWithUV(sp[ii1][0] * r, sp[ii1][1] * r, sp[ii1][2] * r, maxU, minV);
		        tessellator.addVertexWithUV(sp[ii2][0] * r, sp[ii2][1] * r, sp[ii2][2] * r, minU, minV);
		        tessellator.addVertexWithUV(sp[ii3][0] * r, sp[ii3][1] * r, sp[ii3][2] * r, minU, maxV);

		        tessellator.setNormal(0.0F, 1.0F, 0.0F);
		        tessellator.addVertexWithUV(sp[ii3][0] * r, sp[ii3][1] * r, sp[ii3][2] * r, maxU, maxV);
		        tessellator.addVertexWithUV(sp[ii2][0] * r, sp[ii2][1] * r, sp[ii2][2] * r, maxU, minV);
		        tessellator.addVertexWithUV(sp[ii1][0] * r, sp[ii1][1] * r, sp[ii1][2] * r, minU, minV);
		        tessellator.addVertexWithUV(sp[ii0][0] * r, sp[ii0][1] * r, sp[ii0][2] * r, minU, maxV);
			}
		}
		tessellator.draw();
	}

	private static void renderOctahedron(int color, int alpha)
	{
		Tessellator tessellator = Tessellator.instance;
		float[][] oc = ModelSolid.octahedron;
		double minU = 0.0D;
		double maxU = 1.0D;
		double minV = 0.0D;
		double maxV = 1.0D;

		for(int i0 = 0; i0 < 2; ++i0)
		{
			for(int i1 = 0; i1 < 4; ++i1)
			{
				int ii0 = i0 * 4 + i1;
				int ii1 = (i0 + 1) * 4 + i1;
				int ii2 = (i0 + 1) * 4 + (i1 + 1) % 4;
				int ii3 = i0 * 4 + (i1 + 1) % 4;

				tessellator.startDrawingQuads();
				if(color > 0)
				{
					tessellator.setColorRGBA_I(color, alpha);
				}
		        tessellator.setNormal(0.0F, 1.0F, 0.0F);
		        tessellator.addVertexWithUV(oc[ii0][0], oc[ii0][1], oc[ii0][2], maxU, maxV);
		        tessellator.addVertexWithUV(oc[ii1][0], oc[ii1][1], oc[ii1][2], maxU, minV);
		        tessellator.addVertexWithUV(oc[ii2][0], oc[ii2][1], oc[ii2][2], minU, minV);
		        tessellator.addVertexWithUV(oc[ii3][0], oc[ii3][1], oc[ii3][2], minU, maxV);
		        tessellator.draw();

		        tessellator.startDrawingQuads();
		        if(color > 0)
				{
					tessellator.setColorRGBA_I(color, alpha);
				}
		        tessellator.setNormal(0.0F, 1.0F, 0.0F);
		        tessellator.addVertexWithUV(oc[ii3][0], oc[ii3][1], oc[ii3][2], maxU, maxV);
		        tessellator.addVertexWithUV(oc[ii2][0], oc[ii2][1], oc[ii2][2], maxU, minV);
		        tessellator.addVertexWithUV(oc[ii1][0], oc[ii1][1], oc[ii1][2], minU, minV);
		        tessellator.addVertexWithUV(oc[ii0][0], oc[ii0][1], oc[ii0][2], minU, maxV);
		        tessellator.draw();
			}
		}
	}

	private static void renderSpark(double height, int color, int alpha)
	{
		Random random = new Random(432L);
        float i0 = random.nextInt(8) * 0.125F;
		double minU = (double)i0;
		double maxU = (double)(i0 + 0.125F);
		double minV = 0.0D;
		double maxV = 1.0D;
		double minX = 0.0D;
		double maxX = 0.0D;
		double minZ = -0.5D;
		double maxZ = 0.5D;

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
        tessellator.setColorRGBA_I(color, alpha);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);

        tessellator.addVertexWithUV(maxX, 0.0D, maxZ, maxU, maxV);
        tessellator.addVertexWithUV(maxX, height, maxZ, maxU, minV);
        tessellator.addVertexWithUV(minX, height, minZ, minU, minV);
        tessellator.addVertexWithUV(minX, 0.0D, minZ, minU, maxV);

        tessellator.addVertexWithUV(minX, 0.0D, minZ, maxU, maxV);
        tessellator.addVertexWithUV(minX, height, minZ, maxU, minV);
        tessellator.addVertexWithUV(maxX, height, maxZ, minU, minV);
        tessellator.addVertexWithUV(maxX, 0.0D, maxZ, minU, maxV);

        tessellator.draw();
	}

	private static void renderEnergyEffectA(int count, int max, int color)
	{
		Tessellator tessellator = Tessellator.instance;

        if(count > 0)
        {
            RenderHelper.disableStandardItemLighting();
            float f1 = ((float)count + 0) / (float)max;
            float f2 = 0.0F;

            Random random = new Random(432L);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glShadeModel(GL11.GL_SMOOTH);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glDepthMask(false);
            GL11.glPushMatrix();

            for (int i = 0; (float)i < (f1 + f1 * f1) / 2.0F * 60.0F; ++i)
            {
                GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F + f1 * 90.0F, 0.0F, 0.0F, 1.0F);
                tessellator.startDrawing(6);
                float f3 = random.nextFloat() * 20.0F + 5.0F + f2 * 10.0F;
                float f4 = random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
                tessellator.setColorRGBA_I(16777215, (int)(255.0F * (1.0F - f2)));//white
                tessellator.addVertex(0.0D, 0.0D, 0.0D);
                tessellator.setColorRGBA_I(color, 0);
                tessellator.addVertex(-0.866D * (double)f4, (double)f3, (double)(-0.5F * f4));
                tessellator.addVertex(0.866D * (double)f4, (double)f3, (double)(-0.5F * f4));
                tessellator.addVertex(0.0D, (double)f3, (double)(1.0F * f4));
                tessellator.addVertex(-0.866D * (double)f4, (double)f3, (double)(-0.5F * f4));
                tessellator.draw();
            }

            GL11.glPopMatrix();
            GL11.glDepthMask(true);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glShadeModel(GL11.GL_FLAT);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            RenderHelper.enableStandardItemLighting();
        }
	}

	private static void renderEnergyEffectB(int c0, int c1, int max, int color, int alpha)
	{
		Tessellator tessellator = Tessellator.instance;

        if(c1 > 0)
        {
            float f1 = (float)c1 / (float)max;
            Random random = new Random(432L);

            for(int i = 0; (float)i < (f1 + f1 * f1) / 2.0F * 60.0F; ++i)
            {
            	double minU = (float)((c0 + i) % 10) / 10.0F * 0.5F;
            	double maxU = minU + 0.5D;

                GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F + f1 * 90.0F, 0.0F, 0.0F, 1.0F);

                tessellator.startDrawingQuads();
                tessellator.setColorRGBA_I(color, alpha);
                tessellator.setNormal(0.0F, 1.0F, 0.0F);
                tessellator.addVertexWithUV(8.0D, -0.5D, 0.0D, maxU, 1.0D);
                tessellator.addVertexWithUV(8.0D, 0.5D, 0.0D, maxU, 0.0D);
                tessellator.addVertexWithUV(0.0D, 0.5D, 0.0D, minU, 0.0D);
                tessellator.addVertexWithUV(0.0D, -0.5D, 0.0D, minU, 1.0D);
                tessellator.draw();
            }
        }
	}

	private static int mixColor(int color, int r, int g, int b)
	{
		int r0 = (color >> 16 & 255) + r;
        int g0 = (color >> 8 & 255) + g;
        int b0 = (color & 255) + b;

        r0 = r0 < 0 ? 0 : (r0 > 255 ? 255 : r0);
        g0 = g0 < 0 ? 0 : (g0 > 255 ? 255 : g0);
        b0 = b0 < 0 ? 0 : (b0 > 255 ? 255 : b0);

        return (r0<< 16 | g0 << 8 | b0);
	}
}
