builder.database(name: "test") {
  table(name: "persons") {
    column(name: "id", type: "integer", required: true)
    column(name: "name", type: "varchar", size: 30, required: true)
    column(name: "lastname", type: "varchar", size: 30, required: true)
  }
}