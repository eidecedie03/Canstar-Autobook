using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Canstar.Autobook.Data.Entities
{
    public class User
    {
        public int id { get; set; }
        public string LastName { get; set; }
        public string FirstName { get; set; }
        public string EmailAddress { get; set; }
        public string Number { get; set; }
        public string Birthday { get; set; }
        public string Address { get; set; }
        public string GeoLocation { get; set; }
        public string Avatar { get; set; }
        public string UserType { get; set; }
        public string score { get; set; }
        public string Password { get; set; }
        public List<Car> Cars { get; set; }

        [NotMapped]
        public string Error { get; set; }
    }
}
