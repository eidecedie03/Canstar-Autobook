using Canstar.Autobook.Data.Entities;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Canstar.Autobook.Data
{
    public class Context : DbContext
    {
        public Context() : base()
        {
            Database.SetInitializer<Context>(null);
        }

        public DbSet<User> Users { get; set; }
        public DbSet<Car> Cars { get; set; }
        public DbSet<Booking> Bookings { get; set; }
    }
}
