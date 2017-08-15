package com.mapris.reportes.controller;

import com.mapris.modelo.dao.UsuarioFacadeLocal;
import com.mapris.modelo.entitie.Usuario;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

@Named(value = "reporteUsuariosController")
@RequestScoped
public class ReporteUsuariosController {

    @EJB
    private UsuarioFacadeLocal ufl;
    private List<Usuario> usuarios;
    private JasperPrint jp;

    public ReporteUsuariosController() {
    }

    @PostConstruct
    public void init() {
        usuarios = ufl.findAll();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    private void prepararExport() throws JRException {
        Map<String, Object> params = new HashMap<>();
//        params.put("UsuarioDelReporte", "Ismael Suárez");
        JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(usuarios);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + "/WEB-INF/reportes/usuarios/ReportesUsuarios.jasper";
        jp = JasperFillManager.fillReport(reportPath, params, bcds);
    }

    public void exportarPDF() throws IOException, JRException {
        prepararExport();
        ServletOutputStream out = null;
        String contentType = "application/pdf";
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletResponse res = (HttpServletResponse) ec.getResponse();
        res.setContentType(contentType);
        res.addHeader("Content-disposition", "attachment; filename=\"ReporteUsuarios.pdf\"");
        out = res.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jp, out);
        fc.responseComplete();
    }

    private void exportarExcel(HttpServletResponse response, String narchivo, HashMap param) throws IOException {
        
        try {
            prepararExport();
            OutputStream out = response.getOutputStream();
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            JRXlsExporter exporterXLS = new JRXlsExporter();
            exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jp);
            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, arrayOutputStream);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporterXLS.exportReport();
            response.setHeader("Content-disposition", "attachment; filename=ListadoPDF");
            response.setContentType("application/vnd.ms-excel");
            response.setContentLength(arrayOutputStream.toByteArray().length);
            out.write(arrayOutputStream.toByteArray());
            out.flush();
            out.close();
        } catch (JRException jRException) {
            
        } catch (IOException iOException) {
        }

    }

}
