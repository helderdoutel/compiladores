using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Compiladores
{
    class ExercicioParser
    {
        private static int _lookahead;
        private static List<char> validos = new List<char>();

        public ExercicioParser()
        {
            _lookahead = Console.Read();
            validos.Add('-');validos.Add('+');validos.Add('a');
        }
        
        public void Inicio()
        {
            S();
            if (_lookahead != 13)
            {
                Console.WriteLine(false);
            }
            else
            {
                Console.WriteLine(true);
            }
        }


        public void S()
        {
                switch (_lookahead)
                {
                    case '+':
                        match('+');
                        S();
                        S();
                        break;
                    case '-':
                        match('-');
                        S();
                        S();
                        break;
                    case 'a':
                        match('a');
                        break;
                    default:
                        throw new Exception($"Syntax error! {(char)_lookahead}");
                }
        }



        public void match(int t)
        {
            if (t == _lookahead)
                _lookahead = Console.Read();
            else
                throw new Exception("Syntax error");
        }
    }
}
