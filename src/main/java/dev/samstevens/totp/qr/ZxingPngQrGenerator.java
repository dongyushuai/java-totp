package dev.samstevens.totp.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import dev.samstevens.totp.exceptions.QrGenerationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ZxingPngQrGenerator implements QrGenerator {

    private static final QRCodeWriter writer = new QRCodeWriter();
    private int imageSize = 350;

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    public int getImageSize() {
        return imageSize;
    }

    @Override
    public byte[] generate(QrData data) throws QrGenerationException {
        try {
            BitMatrix bitMatrix = writer.encode(data.getUri(), BarcodeFormat.QR_CODE, imageSize, imageSize);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

            return pngOutputStream.toByteArray();
        } catch (WriterException | IOException e) {
            throw new QrGenerationException();
        }
    }
}
