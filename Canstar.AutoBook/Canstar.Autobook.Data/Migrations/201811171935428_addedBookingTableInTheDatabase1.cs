namespace Canstar.Autobook.Data.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class addedBookingTableInTheDatabase1 : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.Cars", "User_id", "dbo.Users");
            DropIndex("dbo.Cars", new[] { "User_id" });
            RenameColumn(table: "dbo.Cars", name: "User_id", newName: "UserId");
            AlterColumn("dbo.Cars", "UserId", c => c.Int(nullable: false));
            CreateIndex("dbo.Cars", "UserId");
            AddForeignKey("dbo.Cars", "UserId", "dbo.Users", "id", cascadeDelete: true);
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Cars", "UserId", "dbo.Users");
            DropIndex("dbo.Cars", new[] { "UserId" });
            AlterColumn("dbo.Cars", "UserId", c => c.Int());
            RenameColumn(table: "dbo.Cars", name: "UserId", newName: "User_id");
            CreateIndex("dbo.Cars", "User_id");
            AddForeignKey("dbo.Cars", "User_id", "dbo.Users", "id");
        }
    }
}
