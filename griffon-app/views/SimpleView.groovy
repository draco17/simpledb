import ca.odell.glazedlists.*
import ca.odell.glazedlists.gui.*
import ca.odell.glazedlists.swing.*
import net.miginfocom.swing.MigLayout

def createTableModel() {
   def columnNames = ["Id", "Name", "Lastname"]
   new EventTableModel(model.persons, [
          getColumnCount: {columnNames.size()},
          getColumnName: {index -> columnNames[index]},
          getColumnValue: {object, index ->
             object."${columnNames[index].toLowerCase()}"
          }] as TableFormat)
}

actions {
  action(id: "addPersonAction",
    name: "Add",
    enabled: bind{ model.enabled },
    closure: controller.addPerson)
}

application(title: "Simple DB",
  size:[320,400],
  locationByPlatform: true,
  iconImage: imageIcon('/griffon-icon-48x48.png').image,
  iconImages: [imageIcon('/griffon-icon-48x48.png').image,
               imageIcon('/griffon-icon-32x32.png').image,
               imageIcon('/griffon-icon-16x16.png').image]
) {
   panel(layout: new MigLayout("fill","[left]")) {
      label("Person", constraints: "split, span, gaptop 10")
      separator( constraints: "growx, wrap, gaptop 10")
      label("Name:")
      textField(id: "name", columns: 30, constraints: "wrap",
        text: bind(target: model, targetProperty: "name", mutual: true))
      label("Lastname:")
      textField(id: "lastname", columns: 30, constraints: "wrap",
        text: bind(target: model, targetProperty: "lastname", mutual: true))
      button(addPersonAction, constraints: "span, grow, wrap")

      label("Persons", constraints: "split, span, gaptop 10")
      separator( constraints: "growx, wrap, gaptop 10")
      scrollPane(constraints: "span, grow") {
         table(id: "personTable", model: createTableModel())
      }
      new TableComparatorChooser(personTable,
          model.persons, AbstractTableComparatorChooser.SINGLE_COLUMN)
   }
}