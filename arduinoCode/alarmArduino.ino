#include <RTClib.h>

// SparkFun Serial LCD example 1
// Clear the display and say "Hello World!"

// This sketch is for Arduino versions 1.0 and later
// If you're using an Arduino version older than 1.0, use
// the other example code available on the tutorial page.

// Use the Software Serial library to create a new "soft" serial port
// for the display. This prevents display corruption when uploading code.
#include <SoftwareSerial.h>

// Attach the serial enabld LCD's RX line to digital pin 11
SoftwareSerial LCD(10, 11); // Arduino SS_RX = pin 10 (unused), Arduino SS_TX = pin 11 

int h=1;
int m=0;
int s=0;
int flag=0; //AM

int ah = 1;
int am=0;
int as=0;
int aflag=0; //AM


int button1;
int button2;
int alarmToggle;

int hs = 3;
int ms = 2;
int ss = 4;

int buzzer = 7;

bool alarmOn = false;


void setup()
{
  Serial.begin(9600);
  LCD.begin(9600); // set up serial port for 9600 baud
  delay(500); // wait for display to boot up

  pinMode(hs,INPUT_PULLUP);// avoid external Pullup resistors for Button 1
  pinMode(ms,INPUT_PULLUP);// and Button 2
  pinMode(ss, INPUT_PULLUP);

  pinMode(buzzer, OUTPUT);
  
}

void loop()
{
  // move cursor to beginning of first line
  selectLineOne();
  // clear display by sending spaces
  clearScreen();


  
  button1=digitalRead(hs);// Read Buttons
  button2=digitalRead(ms);
  Serial.print(button1);
  Serial.print(button2);
  
  alarmToggle = digitalRead(ss);
  Serial.print(alarmToggle);

  if(alarmToggle == 1) {
    if(button1==0){
      h=h+1; 
    }
    if(button2==0) {
      m += 5;
    }
  }
  
  else if(alarmToggle == 0) {
    if(button1==0){
      ah=ah+1; 
    }
    if(button2==0) {
      am += 5;
    }
  }
  


  if(!alarmOn) {
 // PRINT TIME
  selectLineOne();

  if(h < 10) {
    LCD.print(0);
    LCD.print(h);
  } else {
    LCD.print(h);
  }
  
  LCD.print(":");
  
  if(m < 10) {
    LCD.print(0);
    LCD.print(m);
  } else {
    LCD.print(m);
  }
  
  LCD.print(":");

  if(s < 10) {
    LCD.print(0);
    LCD.print(s);
  } else {
    LCD.print(s);
  }

  LCD.print(" ");
  
  if(flag == 1) {
    LCD.print("PM");
  } else {
    LCD.print("AM");
  }

  s++;
  
  if(s >=60) {
    s = 0;
    m++;
  }

  if(m >= 60) {
    m = 0;
    h ++;
  }

  if(h > 12) {
    h = 1;
    if (flag == 1) {
      flag = 0;
    } else {
      flag = 1;
    }
  }


  //PRINT ALARM:

  selectLineTwo();


  LCD.print("Alarm: ");
  
  if(ah < 10) {
    LCD.print(0);
    LCD.print(ah);
  } else {
    LCD.print(ah);
  }
  
  LCD.print(":");
  
  if(am < 10) {
    LCD.print(0);
    LCD.print(am);
  } else {
    LCD.print(am);
  }
  

  LCD.print(" ");
  
  if(aflag == 1) {
    LCD.print("PM");
  } else {
    LCD.print("AM");
  }

  
  if(as >=60) {
    as = 0;
    am++;
  }

  if(am >= 60) {
    am = 0;
    ah ++;
  }

  if(ah > 12) {
    ah = 1;
    if (aflag == 1) {
      aflag = 0;
    } else {
      aflag = 1;
    }
  }

  }



  //PRINT ALARM MESSAGE
  else if(alarmOn) {
    clearScreen();
    selectLineOne();
    LCD.print("WAKE UP!!");

    selectLineTwo();
    LCD.print("Do 10 Pushups!");
  }




  //ALARM BUZZER:
  alarmOn = false;
  if(h == ah && m == am && alarmToggle == 1) {
    alarmOn = true;
  }

  if(alarmOn) {
    tone(buzzer, 400);
  }

  if(h != ah || m != am || alarmToggle == 0) {
      alarmOn = false;
      noTone(buzzer);
    }

  
  delay(1000); 
}

void clearScreen()
{
  //clears the screen, you will use this a lot!
  LCD.write(0xFE);
  LCD.write(0x01); 
}

void selectLineOne()
{ 
  //puts the cursor at line 0 char 0.
  LCD.write(0xFE); //command flag
  LCD.write(128); //position
}
//-------------------------------------------------------------------------------------------
void selectLineTwo()
{ 
  //puts the cursor at line 0 char 0.
  LCD.write(0xFE); //command flag
  LCD.write(192); //position
}

int backlight(int brightness)// 128 = OFF, 157 = Fully ON, everything in between = varied      
{
  //this function takes an int between 128-157 and turns the backlight on accordingly
  LCD.write(0x7C); //NOTE THE DIFFERENT COMMAND FLAG = 124 dec
  LCD.write(brightness); // any value between 128 and 157 or 0x80 and 0x9D
}
