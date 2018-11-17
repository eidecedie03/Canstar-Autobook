namespace Canstar.Autobook.Data.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class addedBookingTableInTheDatabase : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Bookings",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        CarId = c.Int(nullable: false),
                        UserId = c.Int(nullable: false),
                        AmountPerDay = c.Double(nullable: false),
                        TotalAmount = c.Double(nullable: false),
                        NoOfDays = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Cars", t => t.CarId, cascadeDelete: true)
                .ForeignKey("dbo.Users", t => t.UserId, cascadeDelete: true)
                .Index(t => t.CarId)
                .Index(t => t.UserId);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Bookings", "UserId", "dbo.Users");
            DropForeignKey("dbo.Bookings", "CarId", "dbo.Cars");
            DropIndex("dbo.Bookings", new[] { "UserId" });
            DropIndex("dbo.Bookings", new[] { "CarId" });
            DropTable("dbo.Bookings");
        }
    }
}
