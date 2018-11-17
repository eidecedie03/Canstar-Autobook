using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Canstar.Autobook.Data.Entities
{
    public class Booking
    {
        public int Id { get; set; }
        public Car Car { get; set; }
        public int CarId { get; set; }
        public User User { get; set; }
        public int UserId { get; set; }
        public double AmountPerDay { get; set; }
        public double TotalAmount { get; set; }
        public int NoOfDays { get; set; }
    }
}
