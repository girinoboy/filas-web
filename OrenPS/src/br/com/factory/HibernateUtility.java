package br.com.factory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.models.Menu;
import br.com.models.Opcao;
import br.com.models.Perfil;
import br.com.models.PermissaoMenu;
import br.com.models.Questao;
import br.com.models.Questionario;
import br.com.models.Usuario;
import br.com.models.UsuarioPerfil;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class HibernateUtility {

	private static final SessionFactory sessionFactory;
	private static final ThreadLocal sessionThread = new ThreadLocal();
	private static final ThreadLocal transactionThread = new ThreadLocal();


	public static Session getSession() throws Exception {
		try{
			Session session = (Session) sessionThread.get();
			if ((session == null) || (!(session.isOpen()))) {
				session = sessionFactory.openSession();
				sessionThread.set(session);
			}
		}catch(Exception e){
			throw e;
		}
		return ((Session) sessionThread.get());
	}

	public static void closeSession() {
		Session session = (Session) sessionThread.get();
		if ((session != null) && (session.isOpen())) {
			sessionThread.set(null);
			session.close();
		}
	}

	public static void beginTransaction() throws Exception {
		try{
			Transaction transaction = getSession().beginTransaction();
			transactionThread.set(transaction);
		}catch(Exception e){
			throw e;
		}
	}

	public static void commitTransaction() {
		Transaction transaction = (Transaction) transactionThread.get();
		if ((transaction != null) && (!(transaction.wasCommitted())) && (!(transaction.wasRolledBack()))) {
			transaction.commit();
			transactionThread.set(null);
		}
	}

	public static void rollbackTransaction() {
		Transaction transaction = (Transaction) transactionThread.get();
		if ((transaction != null) && (!(transaction.wasCommitted())) && (!(transaction.wasRolledBack()))) {
			transaction.rollback();
			transactionThread.set(null);
		}
	}

	static {
		try {
			sessionFactory =  ((AnnotationConfiguration) new AnnotationConfiguration()

			.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")// tipo de dialeto do banco
			//.setProperty("hibernate.connection.driver_class","net.sourceforge.jtds.jdbc.Driver")// driver do banco
			//.setProperty("hibernate.connection.url", "jdbc:jtds:sqlserver://10.100.100.132:1433/citmensageria")// endereço do banco de dados
			//.setProperty("hibernate.connection.username", "sa")
			//.setProperty("hibernate.connection.password", "CITmensageria123")

			//para tomcat
			//.setProperty("hibernate.connection.datasource", "java:comp/env/jdbc/proserDS")
			//para jboss

//			.setProperty("hibernate.connection.driver_class","org.hsqldb.jdbcDriver") 
//			.setProperty("hibernate.connection.url", "jdbc:hsqldb:file:C:/tmp/satisfacao")
//			.setProperty("hibernate.connection.username", "SA")
//			.setProperty("hibernate.connection.password", "")
			.setProperty("hibernate.connection.datasource", "java:orenDS")
			.setProperty("hibernate.hbm2ddl.auto", "update")
			.setProperty("hibernate.c3p0.max_size", "10")
			.setProperty("hibernate.c3p0.min_size", "2")
			.setProperty("hibernate.c3p0.timeout", "5000")
			.setProperty("hibernate.c3p0.max_statements", "10")
			.setProperty("hibernate.c3p0.idle_test_period", "3000")
			.setProperty("hibernate.c3p0.acquire_increment", "2")
			.setProperty("hibernate.show_sql", "true")
			.setProperty("hibernate.use_outer_join", "true")
			.setProperty("hibernate.generate_statistics", "true")
			.setProperty("hibernate.use_sql_comments", "true")
			.setProperty("hibernate.format_sql", "true")
			//.setProperty("hibernate.order_updates", "true")
			.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider")
			.setProperty("hibernate.current_session_context_class", "thread")
			//.setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory")
			//.setProperty("hibernate.default_schema", "dbo")

			.setProperty("hibernate.validator.apply_to_ddl", "false")
			.setProperty("hibernate.validator.autoregister_listeners", "false")

			//.setProperty("hibernate.query.factory_class", "org.hibernate.hql.internal.classic.ClassicQueryTranslatorFactory")
			//.setProperty("hibernate.query.factory_class", "org.hibernate.hql.classic.ClassicQueryTranslatorFactory")

					)

					//.setProperty("hibernate.connection.autocommit", "true")
					//.setProperty("hibernate.connection.pool_size", "1")
					//CADASTROS abaixo coloque todas classes que deseja ser modelo para criação do banco de dados
					.addAnnotatedClass(Menu.class)
					.addAnnotatedClass(Perfil.class)
					.addAnnotatedClass(PermissaoMenu.class)
					.addAnnotatedClass(Usuario.class)
					.addAnnotatedClass(UsuarioPerfil.class)
					.addAnnotatedClass(Questao.class)
					.addAnnotatedClass(Questionario.class)
					.addAnnotatedClass(Opcao.class)
					//MOVIMENTOS
					//.configure()
					.buildSessionFactory();
			/*
            AnnotationConfiguration annotationConfiguration = null;//.addAnnotatedClass(AdditionalInformation.class);
            annotationConfiguration.addAnnotatedClass(Usuario.class);
            annotationConfiguration.addAnnotatedClass(Usuario.class);
            annotationConfiguration.addAnnotatedClass(Usuario.class);
            annotationConfiguration.addAnnotatedClass(Usuario.class);
            annotationConfiguration.buildSessionFactory();
            sessionFactory=(SessionFactory) annotationConfiguration;

            sessionFactory = new AnnotationConfiguration()

            .addAnnotatedClass(Usuario.class)
            .setProperty("hibernate.dialect", "com.mysql.jdbc.Driver")// tipo de dialeto do banco
            .configure()
            .buildSessionFactory();
			 */
			/*
			try {  
				AnnotationConfiguration configuration = new AnnotationConfiguration();
				//CADASTROS abaixo coloque todas classes que deseja ser modelo para criação do banco de dados
//				for(Class clazz : getClasses("br.con.beans")){  
//					configuration.addAnnotatedClass(clazz);
//				}  
				((AnnotationConfiguration) configuration //configura as propiedades

				.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")// tipo de dialeto do banco
				.setProperty("hibernate.connection.datasource", "java:orenDS")
				.setProperty("hibernate.hbm2ddl.auto", "validate")
				.setProperty("hibernate.c3p0.max_size", "10")
				.setProperty("hibernate.c3p0.min_size", "2")
				.setProperty("hibernate.c3p0.timeout", "5000")
				.setProperty("hibernate.c3p0.max_statements", "10")
				.setProperty("hibernate.c3p0.idle_test_period", "3000")
				.setProperty("hibernate.c3p0.acquire_increment", "2")
				.setProperty("hibernate.show_sql", "true")
				.setProperty("use_outer_join", "true")
				.setProperty("hibernate.generate_statistics", "true")
				.setProperty("hibernate.use_sql_comments", "true")
				.setProperty("hibernate.format_sql", "true")
				//.setProperty("hibernate.order_updates", "true")
				.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider")
				.setProperty("hibernate.current_session_context_class", "thread")
				//.setProperty("hibernate.query.factory_class", "org.hibernate.hql.internal.classic.ClassicQueryTranslatorFactory")
				.setProperty("hibernate.validator.apply_to_ddl", "false")
				.setProperty("hibernate.validator.autoregister_listeners", "false")
				)
				//.setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory")
				//.setProperty("hibernate.default_schema", "dbo")
				.addAnnotatedClass(Menu.class)
				.addAnnotatedClass(Usuario.class)
                .addAnnotatedClass(Permissao.class);
				//.setProperty("hibernate.connection.autocommit", "true")
				//.setProperty("hibernate.connection.pool_size", "1")
				configuration.configure();  
				sessionFactory = configuration.buildSessionFactory();  
			} catch (Exception ex) {  
				//logger.fatal("Something happened here!!! 8-O", ex);
				ex.printStackTrace();
				throw new ExceptionInInitializerError(ex);  
			}  */

			/*
            InitialContext ic = new InitialContext();  
            factory = ( SessionFactory ) ic.lookup( "java:/hibernate/HibernateFactory" );  

            Context context = new InitialContext();  
            Context lautx = (Context) context.lookup("java:comp/env");  
            DataSource ds = (DataSource)lautx.lookup("jdbc/porserDS");*/

		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static List<Class> getClasses(String pckgname) throws ClassNotFoundException {  
		List<Class> classes = new ArrayList<Class>();  
		ClassLoader cld = Thread.currentThread().getContextClassLoader();  
		String path = '/' + pckgname.replace('.', '/');  
		URL resource = cld.getResource(path);  
		File directory = new File(resource.getFile());  
		if (directory.exists()) {  
			String[] files = directory.list();  
			for (int i = 0; i < files.length; i++) {  
				if (files[i].endsWith(".class")) {  
					classes.add(Class.forName(pckgname + '.'  
							+ files[i].substring(0, files[i].length() - 6)));  
				}  
			}  
		}   
		return classes;  
	}  

	public static void main(String [] args) throws Exception {
		/*
    	Context ctx = new InitialContext();
    	ctx.lookup("jdbc/xxxx");

    	Configuration config = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sf = config.buildSessionFactory();

        InitialContext contexto = new InitialContext();  

        String JNDINAME = null;
		DataSource ds = (DataSource)contexto.lookup("java:comp/env/"+JNDINAME);  

        Connection conn = ds.getConnection();  


        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource dss = (DataSource)
        envCtx.lookup("jdbc/myds");


        InitialContext ic = new InitialContext();  
        factory = ( SessionFactory ) ic.lookup( "java:/hibernate/HibernateFactory" ); 


        Context initialCntx = new InitialContext();
SessionFactory sessionFactory = (SessionFactory)initialCntx
     .lookup("java:comp/env/hibernate/SampleSessionFactory");
Session hibernateSess = sessionFactory.getCurrentSession();
		 */
	}

}

