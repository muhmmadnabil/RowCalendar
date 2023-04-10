# RowCalendar

Android library for the horizontal calendar like IOS calendar.


![rowCalendar1](https://user-images.githubusercontent.com/70924560/230875387-9d51039e-a166-4746-8524-e0869bd960ae.gif)
![rowCalendar2](https://user-images.githubusercontent.com/70924560/230875450-11c3fa7d-d177-4059-9ff6-82e1845ebaba.mp4)
<img src="https://user-images.githubusercontent.com/70924560/230875510-37c2d211-b49d-4dcf-b301-947f2d8ac655.gif" width="285" /> 

## FEATURES
* single selection
* multiple selection
* long press selection
* control selection of particular items using selection manager 
* check if the item is selected
* set selection
* and more...

## INSTALLATION
#### GRADLE 

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

```
dependencies {
	 implementation 'com.github.muhmmadnabil:RowCalendar:1.0.0'
}
```

#### MAVEN
```
<dependency>
	    <groupId>com.github.muhmmadnabil</groupId>
	    <artifactId>RowCalendar</artifactId>
	    <version>1.0.0</version>
	</dependency>
```

## USAGE
#### 1. create calendar item view layout files
* create layout files, which will be defining how you calendar will look like <br>
* basic item - selected and deselected <br>

#### 2. create special calendar item view layout files (optional)
* if you need some special items, which will be displayed accroding to your logic you can add them 
* special item - selected and deselected <br>

#### 3. add RowCalendar to your activty or fragment layout file

```xml
 <com.muhmmad.rowcalendar.calendar.RowCalendar
        android:id="@+id/row_calendar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="5dp"
        app:longPress="true" />
```

#### 4. setup CalendarViewManager

```kotlin
 val calendarViewManager = object :
                CalendarViewManager {
                override fun setCalendarResource(isToday: Boolean, isSelected: Boolean): Int {
                    return if (isToday) R.layout.today_calendar_item
                    else if (isSelected) R.layout.selected_calendar_item
                    else R.layout.calendar_item
                }

                override fun bindData(
                    holder: CalendarAdapter.ViewHolder,
                    date: Date,
                    position: Int,
                    isSelected: Boolean
                ) {
                    holder.itemView.findViewById<TextView>(R.id.tv_date_calendar_item).text =
                      DateHelper.getDay(date)

                    holder.itemView.findViewById<TextView>(R.id.tv_day_calendar_item).text =
                     DateHelper.getDayLetter(date)

                }
            }
```

#### 5. initialize RowCalendar

```kotlin
rowCalendar.init(calendarViewManager)
```

#### 6. setup RowCalendar OnClickListener & OnLongPressd
```kotlin
 rowCalendar.onClickListener {
                Toast.makeText(root.context, "click", Toast.LENGTH_SHORT).show()
            }
            rowCalendar.onLongPressed {
                Toast.makeText(root.context, "LongPress", Toast.LENGTH_SHORT).show()
            } 
```
}  

## PARAMETERS AND FUNCTIONS
* ```includeCurrentDate```
  * include the current date with custom layout
* ```multiSelection```
  * enable multiSelection
* ```longPress```
  * enable longPress
* ```getNextMonth()```
  * make the date on the calendar is the next month
* ```getPreviousMonth()```
  * make the date on the calendar is the previous month
* ```onClickListener()```
  * Handle onClickListener
* ```onLongPressed```
  * Handle onLongPress
* ```setCalendarResource()```
  * set the layouts of the calendar
* ```bindData()```
  * Bind the data of the items in calendar

### DateHelper class
You can use DateUtils class when you want get some values from date. 
* ```getDayName(date: Date)```
  * returns day name, for example Friday, Thursday, Monday, etc...
* ```getDay3LettersName(date: Date)```
  * returns day abbreviation, for example Fri, Thu, Mon, etc...
* ```getDayLetter(date: Date)```
  * returns day abbreviation, for example F, T, M, S, etc...
* ```getMonthNumber(date: Date)```
  * returns month number, for example 1, 3, 12, 9, etc...
* ```getMonthName(date: Date)```
  * returns month name, for example December, September, January, etc...

  
## CONTRIBUTE
* the best way to submit a patch is to send me a pull request
* to report a specific problem or feature request, open a new issue on Github

## SEND ME YOUR CREATIONS
* feel free to contact me and send me your app [here](https://github.com/miso01/SingleRowCalendar/blob/docs/CONTACT.md)
* potentially your calendar can be here as a presentation of the library 

## LICENSE 
The library is licensed under [Apache License](https://github.com/miso01/SingleRowCalendar/blob/docs/LICENSE). 
