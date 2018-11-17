using Canstar.Autobook.Data;
using Canstar.Autobook.Data.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Canstar.AutoBook.Controllers
{
    public class BookingController : ApiController
    {
        public HttpResponseMessage Post(Booking booking)
        {
            try
            {
                Context context = new Context();
                using (context)
                {
                    context.Bookings.Add(booking);
                    context.SaveChanges();
                }
                return this.Request.CreateResponse(HttpStatusCode.OK, new { error = "False", message = "Saved Successfully" });
            }
            catch (Exception)
            {
                return this.Request.CreateResponse(HttpStatusCode.OK, new { error = "True", message = "An error occured while saving the booking details" });
            }
        }
    }
}
