import groovy.sql.Sql
import org.javanicus.gsql.*

@Singleton
class DbService {
   private sql

   def initDataSource( dataSource ) {
      sql = Sql.newInstance(
         dataSource.url,
         dataSource.username,
         dataSource.password,
         dataSource.driverClassname
      )

      // read database schema
      def typeMap = new TypeMap()
      def binding = new Binding()
      binding.setVariable("builder",new RelationalBuilder(typeMap))
      def database = new DatabaseSchema(binding).run()

      // create database schema
      def sqlGenerator = new SqlGenerator(typeMap,System.getProperty("line.separator","\n"))
      def writer = new StringWriter()
      sqlGenerator.writer = writer
      sqlGenerator.createDatabase(database,false)
      writer.flush()
      sql.execute(writer.toString())

      // populate database
      binding = new Binding()
      binding.setVariable("sql",sql)
      new Bootstrap(binding).run()
   }

   def getSql() { sql }
}