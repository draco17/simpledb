class SimpleController {
   def model

   def mvcGroupInit( Map args ) {
      doOutside {
         DbService.instance.sql.eachRow("SELECT * FROM persons") {
            model.persons << [id: it.id, name: it.name, lastname: it.lastname]
         }
      }
   }

   def addPerson = { evt = null ->
       def lastId = 0
       model.persons.each{ lastId = it.id > lastId ? it.id : lastId }
       model.persons << [id: lastId+1, name: model.name, lastname: model.lastname]
       model.name = ""
       model.lastname = ""
       model.enabled = false
   }
}