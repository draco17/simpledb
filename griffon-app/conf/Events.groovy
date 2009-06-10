onBootstrapEnd = { app ->
   def dataSource = new ConfigSlurper().parse(DataSource).dataSource
   DbService.instance.initDataSource(dataSource)
}