using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Canstar.Autobook.Data.Entities
{
    public class Person
    {
        public int id { get; set; }
        public string firstName { get; set; }
        public string lastName { get; set; }
        public string email { get; set; }
        public string avatar { get; set; }
        public int score { get; set; }
        public Address address { get; set; }
    }

    public class Address
    {
        public string street { get; set; }
        public string street_secondary { get; set; }
        public string city { get; set; }

        public Geo geo { get; set; }
    }

    public class Geo
    {
        public string latitude { get; set; }
        public string longitude { get; set; }
    }
}
