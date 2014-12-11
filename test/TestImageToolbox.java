import static org.junit.Assert.assertThat;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.BitSet;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

public class TestImageToolbox {

	@Test
	public void testImgToMatrix() throws InterruptedException {
		BufferedImage img = new BufferedImage(1, 2,
				BufferedImage.TYPE_BYTE_GRAY);
		int[] pixels = { 30, 12 };
		img.getRaster().setPixels(0, 0, 1, 2, pixels);
		// JFrame display = new JFrame();
		// display.getContentPane().add(new ImagePanel(img));
		// display.pack();
		// display.setVisible(true);
		// Thread.sleep(1000);

		int[][] matrix = controller.ImageToolbox.imgToMatrix(img);
		// int[][] checkMatrix = { { 50, 44 }, { 50, 50 } };
		int[][] checkMatrix = { { 30, 12 } };
		// System.out.println("" + matrix[0][0] + matrix[0][1] + "; "
		// + checkMatrix[0][0] + checkMatrix[0][1]);

		// System.out.println(Arrays.toString(matrix[0])
		// + Arrays.toString(matrix[1]));
		//
		// System.out.println(Arrays.toString(checkMatrix[0])
		// + Arrays.toString(checkMatrix[1]));
		assertThat(matrix, IsEqual.equalTo(checkMatrix));
	}

	@Test
	public void testBitsetToMatrix() {
		BitSet binval = new BitSet(16);
		binval.set(0);

		int[][] checkMatrix = { { 128, 0 } };// 2x2 matrix
		System.out.println(Arrays.toString(checkMatrix[0]));
		int[][] result = controller.ImageToolbox.bitsetToMatrix(binval, 1, 2);

		System.out.println(Arrays.toString(result[0]));

		assertThat(checkMatrix, IsEqual.equalTo(result));

	}

}
