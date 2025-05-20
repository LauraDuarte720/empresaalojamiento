package co.edu.uniquindio.empresaalojamiento.utilidades;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.nio.file.Path;

import java.io.File;

import jakarta.activation.FileDataSource;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Utilidades {
    public static void enviarNotificacion(String destinatario, String asunto, String mensaje) {


        Email email = EmailBuilder.startingBlank()
                .from("envioemail278@gmail.com")
                .to(destinatario)
                .withSubject(asunto)
                .withPlainText(mensaje)
                .buildEmail();


        try (Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "envioemail278@gmail.com", "jipn vsxx eoxc pwvu")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {


            mailer.sendMail(email);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void enviarNotificacion(String destinatario, String asunto, String mensaje, String rutaQR) {
        Email email = EmailBuilder.startingBlank()
                .from("envioemail278@gmail.com")
                .to(destinatario)
                .withSubject(asunto)
                .withPlainText(mensaje)
                .withAttachment("qr.png", new FileDataSource(new File(rutaQR)))
                .buildEmail();

        try (Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "envioemail278@gmail.com", "jipn vsxx eoxc pwvu")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {

            mailer.sendMail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generarCodigoVerificacion() {
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000); // entre 100000 y 999999
        return String.valueOf(codigo);
    }

    public static void crearQR(String contenido, String rutaImagen) throws WriterException, IOException {
        QRCodeWriter qrWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrWriter.encode(contenido, BarcodeFormat.QR_CODE, 300, 300);
        Path path = new File(rutaImagen).toPath();
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }


}
