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
    public class FindCarController : ApiController
    {
        public HttpResponseMessage Get(int userId)
        {
            double latitude = 0;
            double longitude = 0;
            int score = 0;




            var cars = new List<Car>();
            try
            {
                //10 Kilometer distance
                double radius = 200000;

                //Get all cars 
                Context context = new Context();
                using (context)
                {
                    var user = context.Users.Where(x => x.id == userId).ToList().Last();
                    score = Convert.ToInt32(user.score);
                    latitude = Convert.ToDouble(user.GeoLocation.Split(',')[0]);
                    longitude = Convert.ToDouble(user.GeoLocation.Split(',')[1]);
                    if (score < 600)
                    {
                        //can only get 3 years old and up car
                        cars = context.Cars.Include("User").ToList().Where(x => Convert.ToInt64(x.Year) <= DateTime.Now.Year - 3 && x.AllowBelowAveScore == false && x.User.id != user.id).ToList();
                    }
                    else
                    {
                        cars = context.Cars.Include("User").Where(x => x.User.id != user.id).ToList();
                    }
                }



                //Add if car location is within the radius
                var userLocation = new GeoCoordinate(latitude, longitude);
                var nearCars = new List<Car>();
                foreach (var car in cars)
                {
                    var geo = new GeoCoordinate(Convert.ToDouble(car.User.GeoLocation.Split(',')[0]), Convert.ToDouble(car.User.GeoLocation.Split(',')[1]));
                    //var x = geo.GetDistanceTo(userLocation);
                    if (geo.GetDistanceTo(userLocation) < radius)
                    {
                        car.Distance = geo.GetDistanceTo(userLocation);
                        nearCars.Add(car);
                    }
                }

                return this.Request.CreateResponse(HttpStatusCode.OK, nearCars.OrderBy(x => x.Distance).ToList());
            }
            catch (Exception ex)
            {
                return this.Request.CreateResponse(HttpStatusCode.OK, new { error = "True", message = "" });
            }
        }
    }
}
