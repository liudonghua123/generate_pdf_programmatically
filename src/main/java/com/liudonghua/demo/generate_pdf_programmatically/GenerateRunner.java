package com.liudonghua.demo.generate_pdf_programmatically;

import java.util.Date;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

@Component
public class GenerateRunner implements CommandLineRunner {

	public void run(String... args) throws Exception {

		// v5 code, not works
		// PdfReader reader = new PdfReader("template.pdf");
		// PdfStamper stamper =
		// new PdfStamper(reader, new FileOutputStream("generated.pdf"));
		// AcroFields form = stamper.getAcroFields();
		// form.setGenerateAppearances(true);
		// form.setField("name", "刘东华");
		// form.setField("college", "信息学院");
		// form.setField("other", "计算机技术专业");
		// stamper.setFormFlattening(true);
		// stamper.close();
		// reader.close();

		// v7 code
		// see http://developers.itextpdf.com/content/itext-7-jump-start-tutorial
		// see http://developers.itextpdf.com/content/best-itext-questions-stackoverview/interactive-forms/itext7-how-fill-out-pdf-file-programmatically-acroform-technology
		PdfDocument pdfDoc = new PdfDocument(new PdfReader("template.pdf"), new PdfWriter("generated.pdf"));
		PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
		Map<String, PdfFormField> fields = form.getFormFields();
		fields.get("name").setValue("liudonghua");
		fields.get("hobby").setValue("sleep");
		fields.get("date").setValue(new Date().toLocaleString());
		form.flattenFields();
		pdfDoc.close();
	}

}
