package com.todo.selenium.config;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;;

public class ToDoPage {

	private WebDriver driver;
	private WebElement input;
	private WebElement add;
	private WebElement tasks;
	

	public ToDoPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get("http://localhost:8080/ToDoList/angular");
	}
	public void addTask(String task) {
		input.sendKeys(task);
		add.click();
	}
	public void removeTask(String task) {
		tasks.findElement(By.name(task)).click();
	}
	public boolean taskExists(String task) {
		try {
			tasks.findElement(By.name(task)) ;
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
}
