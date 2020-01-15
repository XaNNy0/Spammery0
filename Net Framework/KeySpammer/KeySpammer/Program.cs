using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using WindowsInput;
using WindowsInput.Native;

namespace KeySpammer
{
    class Program
    {
        static void Main(string[] args)
        {
            IInputSimulator simulator = new InputSimulator();
            IKeyboardSimulator keyboard = new KeyboardSimulator(simulator);

            //Hier gewünschten Key einfügen
            //keyboard.KeyPress(VirtualKeyCode.VK_1);

            //Für Fließtext
            keyboard.TextEntry("Automatisch generiert! \n");

            //Hier gewünschten Timeout in millisec einfügen
            keyboard.Sleep(3000);

            //Nutze ein while(true) loop für unbegrenzte Macht!
            //Verwenden von Command Line Arguments (args) um Anwendung beim Starten zu konfigurieren!
        }
    }
}
