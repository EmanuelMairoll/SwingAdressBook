package view;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;

public class ComponentRegistry {
	private ArrayList<TaggedJComponent> components = new ArrayList<TaggedJComponent>();

	public void add(JComponent component, Tag... tags) {
		components.add(new TaggedJComponent(component, tags));
	}

	public JComponent get(int index) {
		return components.get(index).getComponent();
	}

	public int size() {
		return components.size();
	}

	public ArrayList<JComponent> getTagged(Tag... tags) {
		ArrayList<JComponent> taggedComponents = new ArrayList<JComponent>();

		for (int i = 0; i < components.size(); i++)
			if (components.get(i).isTaggedWith(tags))
				taggedComponents.add(components.get(i).getComponent());

		return taggedComponents;
	}

	private class TaggedJComponent {

		final JComponent component;
		final Tag[] tags;

		public TaggedJComponent(JComponent component, Tag[] tags) {
			this.component = component;
			this.tags = tags;
		}

		public JComponent getComponent() {
			return component;
		}

		public boolean isTaggedWith(Tag... passedTags) {
			List<Tag> thisTags = Arrays.asList(tags);
			for (Tag t : passedTags)
				if (thisTags.contains(t))
					return true;

			return false;
		}

	}
}
