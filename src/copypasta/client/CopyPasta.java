package copypasta.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CopyPasta implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		TextArea nameField = new TextArea();
		nameField.setText("GWT User");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);

		addCutHandler(nameField.getElement());
	}

	public native void addCutHandler(Element elementID)
	/*-{
		var that = this;
		
		elementID.oncopy = function(e) {
			var text = that.@copypasta.client.CopyPasta::getTextForClipboard()();
			var body = $doc.querySelector('body')

			var hidden = document.createElement("textarea");
			hidden.setAttribute("style", "position:absolute;top:0;left:0;margin:-200px;");
			hidden.innerHTML = text;
			body.appendChild(hidden);
			hidden.select();
			document.execCommand('copy');
			setTimeout(function(){ body.removeChild(hidden); }, 1000);
		}
		
		function onPaste(element) {
			that.@copypasta.client.CopyPasta::handleOnPaste(Ljava/lang/String;)(element.value);
		}
		
		elementID.onpaste = function(e) {
			setTimeout(onPaste, 50, this); 
		}
	}-*/;

	public String getTextForClipboard() {
		return "\"Column 1\",\"Column 2\"\n\"Row 2,1\",\"Row 2,2\"";
	}
	
	public void handleOnPaste(String text) {
		Window.alert(text);
	}
}
