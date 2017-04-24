// @flow

import 'babel-polyfill';
import 'zone.js/dist/zone'
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { NgModule, Component }      from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { Injectable } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';

@Component({
    selector: 'app-menu',
    template: '<div class="container">' +
                    '<ul class="nav nav-tabs">' +
                        '<li><a href="/#/">Customers</a></li>' +
                        '<li><a href="/#/about">About</a></li>' +
                    '</ul>' +
                '</div>'
})
class MenuComponent {}

class CustomerService {
    static instance=null;

    // Return singleton
    static get() {
        if(!CustomerService.instance)
            CustomerService.instance=new CustomerService();
        return CustomerService.instance;
    }

    getCustomers() {
        return fetch("/customers").then((response)=>{
            if(!response.ok) {
                throw response.statusText;
            }
            return response.json();
        });
    }

    getCustomer(customerId) {
        return fetch("/customer/"+customerId).then((response)=>{
            if(!response.ok) {
                throw response.statusText;
            }
            return response.json();
        });
    }

    addCustomer(name, city) {
        var body=JSON.stringify({name: name, city: city});
        return fetch("/customers", {method: "POST", headers: new Headers({'Content-Type': 'application/json'}), body: body}).then((response)=>{
            if(!response.ok) {
                throw response.statusText;
            }
            return response.json();
        });
    }

    delCustomer(id) {
        return fetch("/customer/" + id, {method: "DELETE"}).then((response)=>{
            if(!response.ok) {
                throw response.statusText;
            }
        });
    }

    editCustomer(id, name, city) {
        var body=JSON.stringify({name: name, city: city});
        return fetch("/customer/" + id, {method: "PUT", headers: new Headers({'Content-Type': 'application/json'}), body: body}).then((response)=>{
            if(!response.ok) {
                throw response.statusText;
            }
            return response.json();
        });
    }
}

@Component({
    template: `<div class="container"><div><h4>status: {{status}}</h4></div>
             <ul class="list-group">
               <li *ngFor="let customer of customers" class="list-group-item">
                 <a href="#/customer/{{customer.id}}">{{customer.name}}</a>
               </li>
             </ul>
             <div>
               <form (ngSubmit)="$event.preventDefault(); onNewCustomer();" #newCustomerForm="ngForm">
                 <input type="text" id="name" class="form-control" required name="name" [(ngModel)]="newCustomerName">
                 <input type="text" id="city" class="form-control" required name="city" [(ngModel)]="newCustomerCity">
                 <button type="submit" class="btn" [disabled]="!newCustomerForm.form.valid">New Customer</button>
               </form>
             </div>
             <div>
               <form (ngSubmit)="$event.preventDefault(); onDelCustomer();" #delCustomerForm="ngForm">
                 <input type="text" id="name" class="form-control" required name="name" [(ngModel)]="delCustomerName">
                 <button type="submit" class="btn" [disabled]="!delCustomerForm.form.valid">Delete Customer</button>
               </form>
             </div>
            </div>`
})
class CustomerListComponent {
    status="";
    customers=[];
    newCustomerName="";
    newCustomerCity="";
    delCustomerName="";


    constructor() {
        CustomerService.get().getCustomers().then((result)=>{
            this.status="successfully loaded customer list";
            this.customers=result;
        }).catch((reason)=>{
            this.status="error: "+reason;
        });
    }

    onNewCustomer() {
        CustomerService.get().addCustomer(this.newCustomerName, this.newCustomerCity).then((result)=>{
            this.status="successfully added new customer";
            this.customers.push({id: result, name: this.newCustomerName, city: this.newCustomerCity});
            this.newCustomerName="";
            this.newCustomerCity="";
        }).catch((reason)=>{
            this.status="error: "+reason;
        });
    }

    onDelCustomer() {
        CustomerService.get().delCustomer(this.delCustomerName).then((result)=>{
            this.status="successfully removed customer with id: " + (this.delCustomerName);
            this.customers.splice(result, 1);
            this.delCustomerName="";
        }).catch((reason)=>{
            this.status="error: "+reason;
        });
    }
}

@Component({
    template: `<div class="container"><div><h4>Status: {{status}}</h4></div>
             <ul class="list-group">
               <li class="list-group-item">Name: {{customer.name}}</li>
               <li class="list-group-item">City: {{customer.city}}</li>
             </ul>
               <div>
                 <form (ngSubmit)="$event.preventDefault(); onEditCustomer();" #editCustomerForm="ngForm">
                   <input type="text" id="name" class="form-control" required name="name" [(ngModel)]="editCustomerName">
                   <input type="text" id="city" class="form-control" required name="city" [(ngModel)]="editCustomerCity">
                   <button type="submit" class="btn" [disabled]="!editCustomerForm.form.valid">Edit Customer</button>
                 </form>
               </div>
             </div>`

})
class CustomerDetailsComponent {
    status="";
    customer={};

    constructor(route: ActivatedRoute) {
        CustomerService.get().getCustomer(route.params.value.id).then((result)=>{
            this.status="successfully loaded customer details";
            this.customer=result;
        }).catch((reason)=>{
            this.status="error: "+reason;
        });
    }

    onEditCustomer() {
        CustomerService.get().editCustomer(this.customer.id, this.editCustomerName, this.editCustomerCity).then((result)=>{
            this.status="successfully edited customer";
            this.customer = result;
            this.editCustomerName="";
            this.editCustomerCity="";
        }).catch((reason)=>{
            this.status="error: "+reason;
        });
    }
}

@Component({
    template: `<div class="container">
                <ul class="list-group">
                 <li class="list-group-item"><b>Created by:</b> Håkon Paulsen</li>
                 <li class="list-group-item"><b>Øving:</b> 6</li>
                 <li class="list-group-item"><b>Date:</b> 30/03-17</li>
                 <li class="list-group-item"><b>Framework:</b> AngularJS</li>
               </ul>
             </div>`

})
class AboutComponent {}

@Component({
    selector: 'app',
    template: `<app-menu></app-menu>
             <router-outlet></router-outlet>`
})
class AppComponent {}

const routing = RouterModule.forRoot([
    { path: '', component: CustomerListComponent },
    { path: 'customer/:id', component: CustomerDetailsComponent },
]);

@NgModule({
    imports:      [ BrowserModule, routing, FormsModule ],
    declarations: [ MenuComponent, CustomerListComponent, CustomerDetailsComponent, AppComponent ],
    providers: [
        { provide: LocationStrategy, useClass: HashLocationStrategy }
    ],
    bootstrap:    [ AppComponent ]
})
class AppModule {}

platformBrowserDynamic().bootstrapModule(AppModule);