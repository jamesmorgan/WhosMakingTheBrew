package com.morgan.design.adaptor.refactor;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.google.common.collect.Iterators;
import com.morgan.design.R;
import com.morgan.design.db.domain.BrewGroup;
import com.morgan.design.db.domain.BrewPlayer;

public class BrewGroupsExpandableListAdapter extends BaseExpandableListAdapter {

	public LayoutInflater inflater;

	private final List<BrewGroup> brewGroups;

	public BrewGroupsExpandableListAdapter(List<BrewGroup> brewGroups, Context context) {
		this.brewGroups = brewGroups;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.dashboard_brew_group_view_list_child, null);
		}

		BrewPlayer player = (BrewPlayer) getChild(groupPosition, childPosition);

		ChildViewHolder childView = new ChildViewHolder(view);
		childView.playerName.setText(player.getName());

		return view;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.dashboard_brew_group_view_list_parent, null);
		}

		BrewGroup group = brewGroups.get(groupPosition);

		ParentViewHolder parentView = new ParentViewHolder(view);
		parentView.groupName.setText(group.getName());
		parentView.groupSize.setText(Integer.toString(group.getSize()));

		return view;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return Iterators.get(brewGroups.get(groupPosition).getBrewPlayers().iterator(), childPosition);
	}

	@Override
	public Object getGroup(int groupPosition) {
		return brewGroups.get(groupPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return brewGroups.get(groupPosition).getBrewPlayers().size();
	}

	@Override
	public int getGroupCount() {
		return brewGroups.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		return false;
	}

	class ChildViewHolder {
		TextView playerName;

		public ChildViewHolder(View view) {
			playerName = (TextView) view.findViewById(R.id.brew_group_player_name);
		}
	}

	class ParentViewHolder {
		TextView groupName;
		TextView groupSize;

		public ParentViewHolder(View view) {
			groupName = (TextView) view.findViewById(R.id.brew_group_name);
			groupSize = (TextView) view.findViewById(R.id.brew_group_size);
		}
	}
}
