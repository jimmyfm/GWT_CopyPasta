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

		addCopyPastaHandler(nameField.getElement());
	}

	public native void addCopyPastaHandler(Element elementID)
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
			$doc.querySelector('body').removeChild(element); 
		}
		
		elementID.onpaste = function(e) {
			var body = $doc.querySelector('body')

			var hidden = document.createElement("textarea");
			hidden.setAttribute("style", "position:absolute;top:0;left:0;margin:-200px;");
			body.appendChild(hidden);
			hidden.focus();

			setTimeout(onPaste, 50, hidden);
			 
		}
	}-*/;

	/**
	 * This method mock a more complex call in the GWT to retrieve the code to inject in the clipboard instead of the 
	 * content acually copied
	 * @return
	 */
	public String getTextForClipboard() {
		return "\"Column 1\",\"Column 2\"\n\"Row 2,1\",\"Row 2,2\"";
	}
	
	/**
	 * Called after the paste event, reading the text collected from the hidden textarea where we redirected the operation.
	 * This allow GWT to do custom processing of the pasted contents
	 * @param text The pasted contents
	 */
	public void handleOnPaste(String text) {
		Window.alert(text);
	}
}
