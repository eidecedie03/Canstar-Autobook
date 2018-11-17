namespace Canstar.Autobook.Data.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class addedCarsTable : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Cars",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        CarType = c.String(),
                        CarModel = c.String(),
                        Year = c.String(),
                        Rate = c.String(),
                        User_id = c.Int(),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Users", t => t.User_id)
                .Index(t => t.User_id);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Cars", "User_id", "dbo.Users");
            DropIndex("dbo.Cars", new[] { "User_id" });
            DropTable("dbo.Cars");
        }
    }
}
