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
import proyecto2.logic.Bien;
import proyecto2.logic.Dependencia;
import proyecto2.logic.Funcionario;
import proyecto2.logic.Labor;
import proyecto2.logic.Puesto;
import proyecto2.logic.Solicitud;
import proyecto2.logic.Usuario;

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

    public static ModelGeneral instance() {
        if (uniqueInstance == null) {
            try {
                connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            } catch (SQLException e) {
            }
            uniqueInstance = new ModelGeneral();
        }
        return uniqueInstance;
    }

    private ModelGeneral() {
        ses = HibernateUtil.getSessionFactory().openSession();
    }

    public Usuario getUsuario(String username, String password) throws Exception {
        Usuario u = (Usuario) ses.get(Usuario.class, username);

        if (u == null) {
            throw new Exception("Usuario no existe");
        }
        if (u.getPassword().equals(password)) {
            Hibernate.initialize(u.getFuncionario());
//            Hibernate.initialize( u.getFuncionario().getLabors());
            ses.evict(u);
            return u;
        } else {
            throw new Exception("Clave incorrecta");
        }
    }

    public Dependencia getDependencia(String codigo) throws Exception {
        Dependencia d = (Dependencia) ses.get(Dependencia.class, codigo);
        Hibernate.initialize(d.getSolicituds());
        ses.evict(d);
        return d;
    }

    public Bien getBien(int codigo) throws Exception {
        Bien b = (Bien) ses.get(Bien.class, codigo);
//        Hibernate.initialize(b.getActivos());
        ses.evict(b);
        return b;
    }

    public Solicitud getSolicitud(int codigo) throws Exception {
        Solicitud s = (Solicitud) ses.get(Solicitud.class, codigo);
        Hibernate.initialize(s.getBiens());
        ses.evict(s);
        return s;
    }

    public Categoria getCategoria(int codigo) throws Exception {
        Categoria s = (Categoria) ses.get(Categoria.class, codigo);
        ses.evict(s);
        return s;
    }

    public Activo getActivo(String codigo) throws Exception {
        Activo a = (Activo) ses.get(Activo.class, codigo);
        // ??? 
        ses.evict(a);
        return a;
    }

    public void agregarSolicitud(Solicitud solicitud) {
        Transaction t = ses.beginTransaction();
        ses.persist(solicitud);
        t.commit();
    }

    public void actualizarSolicitud(Solicitud solicitud) {
        Transaction t = ses.beginTransaction();
        ses.merge(solicitud);
        t.commit();
    }

    public List<Funcionario> searchFuncionarios(Funcionario func) {
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

    public List<Funcionario> searchAllFuncionarios() {
        String sql = "select * from funcionario";
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
        Transaction t = ses.beginTransaction();
        ses.delete(f);
        t.commit();
    }

    public void eliminaDependencia(Dependencia d) {
        Transaction t = ses.beginTransaction();
        ses.delete(d);
        t.commit();
    }

    public void agregarBien(Bien bien) {
        Transaction t = ses.beginTransaction();
        ses.persist(bien);
        t.commit();
    }

    public void actualizarBien(Bien bien) {
        Transaction t = ses.beginTransaction();
        ses.merge(bien);
        t.commit();
    }

    public void borraBien(Bien bien) {
        Transaction t = ses.beginTransaction();
        ses.delete(bien);
        t.commit();
    }

    public void borrarSolicitud(Solicitud solicitud) {
        Transaction t = ses.beginTransaction();
        ses.delete(solicitud);
        t.commit();
    }

    //public List<Bien> getAllBienes(){
    //    String sql = "select * from bien";
    ///**************************
    public List<Bien> getAllBienesSolicitud(Solicitud solicitud) {
        String sql = "select * from bien where solicitud=" + solicitud.getCodigo();
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Bien> resultado = new ArrayList<Bien>();
            while (rs.next()) {
                Integer.parseInt(rs.getString("Cantidad"));
                resultado.add(new Bien(rs.getString("Descripcion"), rs.getString("Marca"), rs.getString("Modelo"), Integer.parseInt(rs.getString("Cantidad")), Double.parseDouble(rs.getString("Precio"))));
            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Bien> getAllBienesCategoria(Solicitud solicitud) {
//        String sql = "select * from bien where solicitud=" + solicitud.getCodigo();
        String sql = " select * from bien b left join categoria c on b.categoria = c.id_categoria where b.solicitud=" + solicitud.getCodigo();
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Bien> resultado = new ArrayList<Bien>();
            while (rs.next()) {
                if (rs.getString("categoria") != null) {
                    Categoria cat = new Categoria();
                    cat.setConsecutivo(rs.getInt("consecutivo"));
                    cat.setTipo(rs.getString("tipo"));
                    cat.setIdCategoria(rs.getInt("id_categoria"));
                    resultado.add(new Bien(rs.getInt("codigo"),cat, rs.getInt("cantidad"), rs.getString("modelo"), rs.getString("descripcion"), rs.getString("marca"), rs.getDouble("precio")));
                } else {
                resultado.add(new Bien(rs.getInt("codigo"),rs.getString("Descripcion"), rs.getString("Marca"), rs.getString("Modelo"), Integer.parseInt(rs.getString("Cantidad")), Double.parseDouble(rs.getString("Precio"))));
                }   
            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Bien> getBienesFromSolicitud(int codigo) {
        String sql = "select * from bien where solicitud like " + codigo;
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Bien> resultado = new ArrayList<Bien>();
            while (rs.next()) {
                Integer.parseInt(rs.getString("Cantidad"));
                resultado.add(new Bien(rs.getString("Descripcion"), rs.getString("Marca"), rs.getString("Modelo"), Integer.parseInt(rs.getString("Cantidad")), Double.parseDouble(rs.getString("Precio"))));
            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }

    public void close() throws SQLException {
        ses.close();
        connection.close();
        HibernateUtil.stop();
    }

    public List<Dependencia> searchDependencias(Dependencia filtro) {
        String sql = "select * from dependencia where codigo like '%%%s%%'";

        sql = String.format(sql, filtro.getCodigo());
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Dependencia> resultado = new ArrayList<Dependencia>();
            while (rs.next()) {
                resultado.add(new Dependencia(rs.getString("codigo"), rs.getString("nombre")));
            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Dependencia> searchAllDependencias() {
        String sql = "select * from dependencia ";
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Dependencia> resultado = new ArrayList<Dependencia>();
            while (rs.next()) {
                resultado.add(new Dependencia(rs.getString("codigo"), rs.getString("nombre")));
            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }

    public String getRolUsuario(String id) throws Exception {

        Puesto u = (Puesto) ses.get(Puesto.class, getPuestoDeLabor(id));
        if (u == null) {
            throw new Exception("Puesto no existe");
        }
//            Hibernate.initialize( u.getFuncionario().getLabors());
        ses.evict(u);
        return u.getNombre();
//         String sql = "select * from puesto where id_puesto="+getPuestoDeLabor(id);
    }

    public int getPuestoDeLabor(String id) {
        String sql = "select puesto from labor where funcionario= '" + id + "'";
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                int puesto = rs.getInt("puesto");
                return puesto;
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public String getCodigoDependenciaDesdeLabor(String id) throws Exception {
        String sql = "select dependencia from labor where funcionario= '" + id + "'";
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                String codigo = rs.getString("dependencia");
                return codigo;
            }
        } catch (SQLException e) {
        }
        return "";
    }

//    public Dependencia getCodigoDependenciaFromLabor(String id) throws Exception{
//        String sql = "select dependencia from labor where funcionario= '"+id+"'";
//        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                ResultSet rs = stm.executeQuery(sql);) {
//            while (rs.next()) {
//                String codigo = rs.getString("dependencia");
//                return codigo;
//            }
//        } catch (SQLException e) {
//        }
//        return "";
//    }
    public List<Solicitud> searchSolicitudes(Solicitud filtro) throws ParseException {

        String sql = "select * from solicitud where comprobante like '%%%s%%'";
        sql = String.format(sql, filtro.getComprobante());
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Solicitud> resultado = new ArrayList<Solicitud>();
            while (rs.next()) {
                resultado.add(new Solicitud(Integer.parseInt(rs.getString("codigo")), rs.getDate("fecha"), Integer.parseInt(rs.getString("cantidad")), rs.getString("tipoAdquisicion"), rs.getString("estado"), Double.parseDouble(rs.getString("monto")), rs.getString("comprobante")));
            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Solicitud> searchByEstado(String estado, Solicitud filtro) {
        String sql = "select * from solicitud s inner join dependencia d on s.dependencia = d.codigo where comprobante like '%%%s%%' and estado='" + estado + "'";
        sql = String.format(sql, filtro.getComprobante());

        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Solicitud> resultado = new ArrayList<Solicitud>();
            while (rs.next()) {
                Dependencia d = new Dependencia();
                d.setCodigo(rs.getString("codigo"));
                d.setNombre(rs.getString("nombre"));
                resultado.add(new Solicitud(Integer.parseInt(rs.getString("codigo")), rs.getDate("fecha"), Integer.parseInt(rs.getString("cantidad")), rs.getString("tipoAdquisicion"), rs.getString("estado"), Double.parseDouble(rs.getString("monto")), rs.getString("comprobante"), d));

            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Solicitud> findByDependencia_Comprobante(int codigo_Dependencia, Solicitud filter) {
        Query query = ses.createQuery("from Solicitud s where s.dependencia = :codigo and s.comprobante like :comprobante");
        query.setInteger("codigo", codigo_Dependencia);
        query.setString("comprobante", "%" + filter.getComprobante() + "%");
        return query.list();
    }

    public List<Solicitud> findSolicitudByEstado(List<String> estados, Solicitud filter) {
        //Query query = ses.createQuery("from Solicitud s where s.estado in (:estados) and s.comprobante like :comprobante");
        Query query = ses.createQuery("from Solicitud s "
                + "inner join fetch s.dependencia "
                + "where s.estado in (:estados) "
                + "and s.comprobante like :comprobante");

        query.setParameterList("estados", estados);
        query.setString("comprobante", "%" + filter.getComprobante() + "%");
        return query.list();
    }

    public List<Solicitud> searchSolicitudesPorVerificar() {
//         String sql = "select * from solicitud where estado = 'recibido'";
        //String sql ="select * from solicitud s inner join dependencia d on s.dependencia = d.codigo where estado = 'Solicitud recibida'";
        String sql = "select * from solicitud s inner join dependencia d on s.dependencia = d.codigo where estado = 'porVerificar'";
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Solicitud> resultado = new ArrayList<Solicitud>();
            while (rs.next()) {
                Dependencia dep = new Dependencia();
                dep.setCodigo(rs.getString("codigo"));
                dep.setNombre(rs.getString("nombre"));
                resultado.add(new Solicitud(Integer.parseInt(rs.getString("codigo")), rs.getDate("fecha"), Integer.parseInt(rs.getString("cantidad")), rs.getString("tipoAdquisicion"), rs.getString("estado"), Double.parseDouble(rs.getString("monto")), rs.getString("comprobante"), dep));
            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }

//     public Labor searchLabor(String id){
//        String sql = "select * from labor where funcionario = " + id;
//
//        sql = String.format(sql, id);
//        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                ResultSet rs = stm.executeQuery(sql);) {
//            Labor resultado = new Labor();
//            while (rs.next()) {
//                Dependencia dep = new Dependencia();
//                dep.setCodigo(rs.getString("codigo"));
//                dep.setNombre(rs.getString("nombre"));
//                Funcionario fun = new Funcionario();
//                fun.setId(rs.getString("id"));
//                fun.setNombre(rs.getString("nombre"));
//                Puesto pue = new Puesto();
//                pue.setIdPuesto(rs.getInt("id_puesto"));
//                pue.setNombre(rs.getString("nombre"));
//                resultado = new Labor(dep, fun, pue);
//            }
//            return resultado;
//        } catch (SQLException e) {
//            return null;
//        }
//    }
    public List<Funcionario> getRegistradores() {
        String sql = "select * from labor l inner join funcionario f on l.funcionario = f.id where puesto=2";//*** poner codigo registrador
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

    public List<Solicitud> solicitu(String estado, Solicitud filtro) {
        String sql = "select * from solicitud s inner join dependencia d on s.dependencia = d.codigo where comprobante like '%%%s%%' and estado='" + estado + "'";
        sql = String.format(sql, filtro.getComprobante());

        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Solicitud> resultado = new ArrayList<Solicitud>();
            while (rs.next()) {
                Dependencia d = new Dependencia();
                d.setCodigo(rs.getString("codigo"));
                d.setNombre(rs.getString("nombre"));
                resultado.add(new Solicitud(Integer.parseInt(rs.getString("codigo")), rs.getDate("fecha"), Integer.parseInt(rs.getString("cantidad")), rs.getString("tipoAdquisicion"), rs.getString("estado"), Double.parseDouble(rs.getString("monto")), rs.getString("comprobante"), d));

            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Solicitud> solicitudesDeRegistrador(String id) {
        String sql = "select * from solicitud s inner join dependencia d on s.dependencia = d.codigo where funcionario='" + id + "'";
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Solicitud> resultado = new ArrayList<Solicitud>();
            while (rs.next()) {
                Dependencia d = new Dependencia();
                d.setCodigo(rs.getString("codigo"));
                d.setNombre(rs.getString("nombre"));
                resultado.add(new Solicitud(Integer.parseInt(rs.getString("codigo")), rs.getDate("fecha"), Integer.parseInt(rs.getString("cantidad")), rs.getString("tipoAdquisicion"), rs.getString("estado"), Double.parseDouble(rs.getString("monto")), rs.getString("comprobante"), d));
            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }

    public void borraCategoria(Categoria c) {
        Transaction t = ses.beginTransaction();
        ses.delete(c);
        t.commit();
    }

    public List<Solicitud> solicitudesRegistradorSearch(String estado, String id, Solicitud filter) {
        String sql = "select * from solicitud s inner join dependencia d on s.dependencia = d.codigo where s.estado='" + estado + "' and funcionario='" + id + "'";

        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Solicitud> resultado = new ArrayList<Solicitud>();
            while (rs.next()) {
                Dependencia d = new Dependencia();
                d.setCodigo(rs.getString("codigo"));
                d.setNombre(rs.getString("nombre"));
                resultado.add(new Solicitud(Integer.parseInt(rs.getString("codigo")), rs.getDate("fecha"), Integer.parseInt(rs.getString("cantidad")), rs.getString("tipoAdquisicion"), rs.getString("estado"), Double.parseDouble(rs.getString("monto")), rs.getString("comprobante"), d));
            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Categoria> searchAllCategoria() {
        String sql = "select * from categoria";
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Categoria> resultado = new ArrayList<Categoria>();
            while (rs.next()) {
                resultado.add(new Categoria(rs.getInt("consecutivo"), rs.getString("tipo"), rs.getInt("id_categoria")));
            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Labor> searchLabors() {
        String sql = "select * from labor";
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Labor> resultado = new ArrayList<Labor>();
            while (rs.next()) {
                Dependencia d = new Dependencia();
                d.setCodigo(rs.getString("dependencia"));
                Funcionario f = new Funcionario();
                f.setId(rs.getString("funcionario"));
                resultado.add(new Labor(rs.getInt("id_labor"), d, f));
            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }

    }

    public List<Categoria> searchCategoria(Categoria categoria) {
        String sql = "select * from categoria where tipo like '%%%s%%'";
        sql = String.format(sql, categoria.getTipo());
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Categoria> resultado = new ArrayList<Categoria>();
            while (rs.next()) {
                resultado.add(new Categoria(rs.getInt("consecutivo"), rs.getString("tipo"), rs.getInt("id_categoria")));
            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }

//    public int getUltimoCodigoSolicitud(){ BORRAR
//        String sql = "select * from solicitud";
//        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                ResultSet rs = stm.executeQuery(sql);) {
//            rs.last();
//            return rs.getInt("codigo");
//        } catch (SQLException e) { }
//        return 0;
//    }
    public List<Solicitud> searchSolicitudesFromDependencia(String id) throws Exception {
        //String sql = "select * from solicitud s inner join dependencia d on s.dependencia = d.codigo where nombre = '" + nombre + "'";
        String sql = "select * from solicitud where dependencia = '" + this.getCodigoDependenciaDesdeLabor(id) + "'";
        //sql = String.format(sql, filtro.getComprobante());
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Solicitud> resultado = new ArrayList<Solicitud>();
            while (rs.next()) {
                resultado.add(new Solicitud(Integer.parseInt(rs.getString("codigo")), rs.getDate("fecha"), Integer.parseInt(rs.getString("cantidad")), rs.getString("tipoAdquisicion"), rs.getString("estado"), Double.parseDouble(rs.getString("monto")), rs.getString("comprobante")));
            }
            return resultado;
        } catch (SQLException e) {

            return null;
        }
    }

    public List<Activo> searchActivos(Activo filtro) {
        String comodin = "";
        //String sql = "select * from activo a inner join labor l";
        String sql = "select * from activo where labor is NULL";
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Activo> resultado = new ArrayList<Activo>();
            while (rs.next()) {
                if (!comodin.equals(rs.getString("codigo"))) {
//                    Dependencia d = new Dependencia();
//                    d.setCodigo(rs.getString("dependencia"));
//                   // d.setNombre(rs.getString("nombre"));
//                    Funcionario f = new Funcionario();
//                    f.setId(rs.getString("funcionario"));
//                    Labor l = new Labor(d, f);
                    //resultado.add(new Activo(rs.getString("codigo"), l));
                    resultado.add(new Activo(rs.getString("codigo")));
                }
                comodin = rs.getString("codigo");
            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Funcionario> searchAllFuncionariosFromDependencia(String codigo) {
        String sql = "select * from labor l inner join funcionario f on l.funcionario = f.id where l.dependencia ='" + codigo + "'";
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

    public List<Funcionario> searchAllFuncionariosFromDependencia(String codigo, Funcionario func) {
        String sql = "select * from labor l inner join funcionario f on l.funcionario = f.id where l.dependencia ='" + codigo + "' and f.id like '%%%s%%'";
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

    public List<Puesto> findAllPuestos() {
        String sql = "select * from puesto";
        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stm.executeQuery(sql);) {
            List<Puesto> resultado = new ArrayList<Puesto>();
            while (rs.next()) {
                resultado.add(new Puesto(rs.getString("nombre"), rs.getInt("id_puesto")));
            }
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }

//    public List<Activo> findActivosPorDescripcion(){
//         String sql = "select * from puesto";
//        try (Statement stm = proyecto2.logic.ModelGeneral.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                ResultSet rs = stm.executeQuery(sql);) {
//            List<Activo> resultado = new ArrayList<Activo>();
//            while (rs.next()) {
//                resultado.add(new Puesto(rs.getString("nombre"), rs.getInt("id_puesto")));
//            }
//            return resultado;
//        } catch (SQLException e) {
//            return null;
//        }
//    }
}
