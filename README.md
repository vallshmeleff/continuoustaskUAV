# continuoustaskUAV
The task of continuous operation of the application - the timer as a service and the receiver of broadcasts
The timer runs as a service in the Timer_Service class. Writes to the LOG every 10 seconds. Sends a signal to a BroadcastReceiver that writes the time to a tv_timer (TextView). Continues to work when the smartphone is blocked.
The problem of continuous operation is solved. Now let's try sending SMS messages in the background and publish the Java code. There's nothing complicated
