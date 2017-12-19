package ru.job4j.templates;

public abstract class TemplateAction {

	public TemplateAction(String name) {
	}
	abstract void start();
	
	abstract void finish();
	
	public void work() {
		this.start();
		this.finish();
	}
	
	public static void main(String[] args) {
		new TemplateAction("String"){
			public void start() {
			}
			public void finish() {
			}
		};
	}
	
}