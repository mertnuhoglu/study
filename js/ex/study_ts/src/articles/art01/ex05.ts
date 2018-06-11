class Menu {
  items: Array<string>;  
  pages: number;         
  constructor(item_list: Array<string>, total_pages: number) {
    this.items = item_list;    
    this.pages = total_pages;
  }
  list(): void {
    console.log("menu:");
    for(var i=0; i < this.items.length; i++) {
      console.log(this.items[i]);
    }
  }
} 
var sundayMenu = new Menu(["pancakes","waffles"], 1);
sundayMenu.list();
// menu:
// pancakes
// waffles
