package tn.iset.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@RestController
@CrossOrigin("*")
public class Impression {
		@Autowired
	private DataSource datasource;
		@RequestMapping(value="/ListeAgent")
		 private HttpEntity<byte[]> Agent() throws JRException, IOException, ClassNotFoundException, SQLException{
			InputStream input=this.getClass().getResourceAsStream("rep.jrxml");
			JasperDesign design=JRXmlLoader.load(input);
		JasperReport jasperReport = JasperCompileManager.compileReport(design);
			//JRDataSource dataSource =new JRResultSetDataSource ((ResultSet) connection); 
		JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,null,datasource.getConnection());
		HttpHeaders header = new HttpHeaders();
		final byte[] data;
		//exporting data to web browser at the end we push it...
		//check last code snippet
		data = JasperExportManager.exportReportToPdf(jasperPrint);
		header.setContentType(MediaType.APPLICATION_PDF);
		header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename="+"etat"+".pdf");
		header.setContentLength(data.length);
		return new HttpEntity<byte[]>(data, header);
		 }
		}
