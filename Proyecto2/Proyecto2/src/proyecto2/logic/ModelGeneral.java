/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import proyecto2.data.HibernateUtil;

/**
 *
 * @author Rodrigo Meléndez
 */
public class ModelGeneral {
    Session ses;
    private static Connection connection;
    
    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        ModelGeneral.connection = connection;
    }
     
    private static ModelGeneral uniqueInstance;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONN_STRING = "jdbc:mysql://localhost/activos";
    
    public static ModelGeneral instance(){
        if (uniqueInstance == null){
            try{
                connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            }catch(SQLException e){}
            uniqueInstance = new ModelGeneral();
        }
        return uniqueInstance; 
    }
    private ModelGeneral(){
        ses = HibernateUtil.getSessionFactory().openSession();
    }
            
    public  Usuario getUsuario(String username, String password) throws Exception{
        Usuario u = (Usuario) ses.get(Usuario.class, username);
        
        if(u==null)  throw new Exception ("Usuario no existe");
        if (u.getPassword().equals(password)){
            Hibernate.initialize( u.getFuncionario());
//            Hibernate.initialize( u.getFuncionario().getLabors());
            ses.evict(u);
            return u;
        }
        else{
            throw new Exception ("Clave incorrecta");
        }
    }

    public  Dependencia getDependencia(int codigo) throws Exception{
        Dependencia d = (Dependencia) ses.get(Dependencia.class, codigo);
        Hibernate.initialize(d.getSolicituds());
        ses.evict(d);
        return d;
    }
    
    public  Solicitud getSolicitud(int codigo) throws Exception{
        Solicitud s = (Solicitud) ses.get(Solicitud.class, codigo);
        Hibernate.initialize(s.getBiens());
        ses.evict(s);
        return s;
    }
    
    public void agregarSolicitud(Solicitud solicitud){
        Transaction t = ses.beginTransaction();
        ses.persist(solicitud);
        t.commit();        
    }

    public void actualizarSolicitud(Solicitud solicitud){
        Transaction t = ses.beginTransaction();
        ses.merge(solicitud);
        t.commit();        
    }
    public List<Funcionario> searchFuncionarios(Funcionario func){
        String sql = "select * from funcionario where id like '%%%s%%'";

        sql = String.format(sql, func.getId());
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Funcionario> resultado = new ArrayList<Funcionario>();
            while (rs.next()) {
                resultado.add(new Funcionario(rs.getString("id"), rs.getString("nombre")));
            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }
    public void eliminaFuncionario(Funcionario f) {
        String sql = "delete from persona where id='%s'";
        sql = String.format(sql, f.getId());
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
        } catch (SQLException e) {}
    }
    public void agregarBien(Bien bien) {
        Transaction t = ses.beginTransaction();
        ses.persist(bien);
        t.commit();
    }
    
    public void actualizarBien(Bien bien){
        Transaction t = ses.beginTransaction();
        ses.merge(bien);
        t.commit();        
    }
    
    public void borraBien(Bien bien){
        Transaction t = ses.beginTransaction();
        ses.delete(bien);
        t.commit();        
    }
    
    public List<Bien> searchBien(Bien filter){
        String sql = "select * from funcionario where id like '%%%s%%'";

        sql = String.format(sql, filter.getCodigo());
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Bien> resultado = new ArrayList<Bien>();
            while (rs.next()) {
                Integer.parseInt(rs.getString("Cantidad"));
                resultado.add(new Bien(rs.getString("Descripcion"), rs.getString("Marca"), rs.getString("Modelo"),Integer.parseInt(rs.getString("Cantidad")), Double.parseDouble(rs.getString("Precio"))));
            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }
    
    public void close() throws SQLException{
        ses.close();
        connection.close();
        HibernateUtil.stop();
    }
    
    public List<Dependencia> searchDependencias(Dependencia filtro){   
        String sql="select * from dependencia where codigo like '%%%s%%'";
        
        sql=String.format(sql, filtro.getCodigo());
        try(Statement stm= proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet rs=stm.executeQuery(sql);){
            List<Dependencia> resultado=new ArrayList<Dependencia>();
            while(rs.next()){
                resultado.add(new Dependencia(rs.getString("codigo"),rs.getString("nombre")));
            }
            return resultado;
        }catch(SQLException e){
            return null;
        }
    }
   
    public String getRolUsuario(String id) throws Exception{
        
         Puesto u = (Puesto) ses.get(Puesto.class, getPuestoDeLabor(id));
        if(u==null)  throw new Exception ("Puesto no existe");
//            Hibernate.initialize( u.getFuncionario().getLabors());
            ses.evict(u);
            return u.getNombre();
       
//         String sql = "select * from puesto where id_puesto="+getPuestoDeLabor(id);
//        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                ResultSet rs = stm.executeQuery(sql);) {
//            while (rs.next()) {
//                return rs.getString("nombre");
//            }
//        } catch (SQLException e) {}
//        return "";
    }
    
    public int getPuestoDeLabor(String id){
        String sql = "select puesto from labor where funcionario= '"+id+"'";
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                int puesto = rs.getInt("puesto");
                return puesto;
            }
        } catch (SQLException e) {        }
       return 0;
    }

    public List<Solicitud> searchSolicitudes(Solicitud filtro) throws ParseException{
       
        String sql="select * from solicitud where comprobante like '%%%s%%'";
        sql=String.format(sql, filtro.getComprobante());
        try(Statement stm=proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet rs=stm.executeQuery(sql);){
            List<Solicitud> resultado=new ArrayList<Solicitud>();
            while(rs.next()){
               resultado.add(new Solicitud(Integer.parseInt(rs.getString("codigo")),rs.getDate("fecha"),Integer.parseInt(rs.getString("cantidad")),rs.getString("tipoAdquisicion"),rs.getString("estado"),Double.parseDouble(rs.getString("monto")),rs.getString("comprobante")));
            }
            return resultado;
        }catch(SQLException e){
            return null;
        }
    }
    

    public List<Solicitud> searchByEstado(String estado,Solicitud filtro) {
        String sql = "select * from solicitud s inner join dependencia d on s.dependencia = d.codigo where comprobante like '%%%s%%' and estado='"+estado+"'";
        sql=String.format(sql, filtro.getComprobante());
        
        try(Statement stm=proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet rs=stm.executeQuery(sql);){
            List<Solicitud> resultado=new ArrayList<Solicitud>();
            while(rs.next()){
                Dependencia d=new Dependencia();
                d.setCodigo(rs.getString("codigo"));
                d.setNombre(rs.getString("nombre"));
                resultado.add(new Solicitud(Integer.parseInt(rs.getString("codigo")),rs.getDate("fecha"),Integer.parseInt(rs.getString("cantidad")),rs.getString("tipoAdquisicion"),rs.getString("estado"),Double.parseDouble(rs.getString("monto")),rs.getString("comprobante"),d));
                
            }
            return resultado;
        }catch(SQLException e){
            return null;
        }
    } 
    
    public List<Solicitud> findByDependencia_Comprobante(int codigo_Dependencia, Solicitud filter){
        Query query = ses.createQuery("from Solicitud s where s.dependencia = :codigo and s.comprobante like :comprobante");
        query.setInteger("codigo", codigo_Dependencia);
        query.setString("comprobante","%"+filter.getComprobante()+"%");
        return query.list();
    }

    public List<Solicitud> findSolicitudByEstado(List<String>estados, Solicitud filter){
        //Query query = ses.createQuery("from Solicitud s where s.estado in (:estados) and s.comprobante like :comprobante");
        Query query = ses.createQuery("from Solicitud s "
                + "inner join fetch s.dependencia "
                + "where s.estado in (:estados) "
                + "and s.comprobante like :comprobante");
        
        query.setParameterList("estados", estados);
        query.setString("comprobante","%"+filter.getComprobante()+"%");
        return query.list();
    }
    

      public List<Solicitud> searchByEstado(){
//         String sql = "select * from solicitud where estado = 'recibido'";
          String sql ="select * from solicitud s inner join dependencia d on s.dependencia = d.codigo where estado = 'recibido'";
         try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                 ResultSet rs = stm.executeQuery(sql);) {
             List<Solicitud> resultado = new ArrayList<Solicitud>();
             while (rs.next()) {
                 Dependencia dep = new Dependencia();
                 dep.setCodigo(rs.getString("codigo"));
                 dep.setNombre(rs.getString("nombre"));
                 resultado.add(new Solicitud(Integer.parseInt(rs.getString("codigo")), rs.getDate("fecha"), Integer.parseInt(rs.getString("cantidad")), rs.getString("tipoAdquisicion"), rs.getString("estado"), Double.parseDouble(rs.getString("monto")), rs.getString("comprobante"),dep));
             }
             return resultado;
         } catch (SQLException e) {
             return null;
        }
     }
      
     public Labor searchLabor(String id){
        String sql = "select * from labor where funcionario = " + id;

        sql = String.format(sql, id);
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            Labor resultado = new Labor();
            while (rs.next()) {
                Dependencia dep = new Dependencia();
                dep.setCodigo(rs.getString("codigo"));
                dep.setNombre(rs.getString("nombre"));
                Funcionario fun = new Funcionario();
                fun.setId(rs.getString("id"));
                fun.setNombre(rs.getString("nombre"));
                Puesto pue = new Puesto();
                pue.setIdPuesto(rs.getInt("id_puesto"));
                pue.setNombre(rs.getString("nombre"));
                resultado = new Labor(dep, fun, pue);
            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }


}
