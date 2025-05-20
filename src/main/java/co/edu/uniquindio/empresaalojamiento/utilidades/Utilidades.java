package co.edu.uniquindio.empresaalojamiento.utilidades;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.activation.FileDataSource;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

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
        } catch (Exception e) {
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

    public static void enviarNotificacion(String destinatario, String asunto, String mensaje, String rutaQR, File archivoAdjunto) {
        Email email = EmailBuilder.startingBlank()
                .from("envioemail278@gmail.com")
                .to(destinatario)
                .withSubject(asunto)
                .withPlainText(mensaje)
                .withAttachment("qr.png", new FileDataSource(new File(rutaQR)))
                .withAttachment(archivoAdjunto.getName(), new FileDataSource(archivoAdjunto))
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

    public static void enviarCorreoQrPdf(String correoDestinatario, String asunto, String mensaje, String nombreImagen, File archivoAdjunto) throws WriterException, IOException {
        try {
            String carpetaQR = "imagenesQr/";
            new File(carpetaQR).mkdirs();

            String rutaQR = carpetaQR + nombreImagen + ".png";

            String contenidoQR = "https://drive.google.com/tu_archivo.pdf";

            Utilidades.crearQR(contenidoQR, rutaQR);

            Utilidades.enviarNotificacion(
                    correoDestinatario,
                    asunto,
                    mensaje,
                    rutaQR,
                    archivoAdjunto
            );

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static File generarPdf(String nombreArchivo, String titulo, String mensajeCentral) throws IOException {
        PDDocument documento = new PDDocument();
        PDPage pagina = new PDPage(PDRectangle.LETTER);
        documento.addPage(pagina);

        try (PDPageContentStream contenido = new PDPageContentStream(documento, pagina)) {
            // Título arriba
            contenido.beginText();
            contenido.setFont(PDType1Font.HELVETICA_BOLD, 20);
            contenido.newLineAtOffset(220, 700); // posición del título
            contenido.showText(titulo);
            contenido.endText();

            // Mensaje en el centro
            contenido.beginText();
            contenido.setFont(PDType1Font.HELVETICA, 14);
            contenido.newLineAtOffset(100, 400); // posición del mensaje
            contenido.showText(mensajeCentral);
            contenido.endText();
        }

        String rutaArchivo = "facturas/" + nombreArchivo + System.currentTimeMillis() + ".pdf";
        documento.save(rutaArchivo);
        documento.close();

        return new File(rutaArchivo);
    }
}