using Canstar.Autobook.Data;
using Canstar.Autobook.Data.Entities;
using System;
using System.Collections.Generic;
using System.Device.Location;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Canstar.AutoBook.Controllers
{
    public class CarsController : ApiController
    {
        public HttpResponseMessage Get(int userId)
        {
            var carList = new List<Car>();
            try
            {
                Context context = new Context();
                using (context)
                {
                     carList = carList = context.Cars.Where(x => x.UserId == userId).ToList();
                }

                return this.Request.CreateResponse(HttpStatusCode.OK, carList);
            }
            catch (Exception ex)
            {
                return this.Request.CreateResponse(HttpStatusCode.OK, new { error = "True", message = "" });
            }
        }

        public string Post(Car car)
        {
            try
            {
                Context context = new Context();
                using (context)
                {
                    context.Cars.Add(car);
                    context.SaveChanges();
                }

                return "Car saved successfully";
            }
            catch (Exception)
            {
                return "Error occured while saving the car";
            }
        }
    }
}
