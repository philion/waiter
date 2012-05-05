/**
 * This file is Copyright © 2008 Software Craftsmen Limited. All Rights Reserved.
 */
package com.softwarecraftsmen.dns;

import com.softwarecraftsmen.ConvenientArrayList;
import static com.softwarecraftsmen.dns.names.DomainName.domainName;
import static com.softwarecraftsmen.dns.MailBox.mailBox;
import static com.softwarecraftsmen.dns.labels.SimpleLabel.simpleLabel;
import static com.softwarecraftsmen.dns.labels.SimpleLabel.Empty;
import com.softwarecraftsmen.dns.labels.Label;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.util.List;

public class MailBoxTest
{
	@Test
	public void toLabelsMatchesExactStructure()
	{
		final List<Label> labels = mailBox("raph", domainName("softwarecraftsmen.com")).toLabels();
		assertThat(new ConvenientArrayList<Label>(simpleLabel("raph"), simpleLabel("softwarecraftsmen"), simpleLabel("com"), Empty), equalTo(labels));
	}
}
