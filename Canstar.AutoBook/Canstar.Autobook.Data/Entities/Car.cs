using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Canstar.Autobook.Data.Entities
{
    public class Car
    {
        public int Id { get; set; }
        public string CarBrand { get; set; }
        public string CarModel { get; set; }
        public string Year { get; set; }
        //public string Rate { get; set; }
        public User User { get; set; }
        public int UserId { get; set; }
        public bool AllowBelowAveScore { get; set; }

        [NotMapped]
        public double Distance { get; set; }
    }
}
