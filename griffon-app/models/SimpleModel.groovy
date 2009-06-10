import groovy.beans.Bindable
import ca.odell.glazedlists.*
import java.beans.PropertyChangeListener

class SimpleModel {
   @Bindable String name
   @Bindable String lastname
   @Bindable boolean enabled = false

   EventList persons = new SortedList( new BasicEventList(),
     {a, b -> a.id <=> b.id} as Comparator)

   SimpleModel() {
      addPropertyChangeListener({ e ->
         if( e.propertyName == "enabled" ) return
         if( name && lastname ) enabled = true
      } as PropertyChangeListener)
   }
}