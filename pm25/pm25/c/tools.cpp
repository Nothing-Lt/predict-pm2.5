#include "tools.h"
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
void correct_time()
{
	system("sudo ntpd -s -d");
}
