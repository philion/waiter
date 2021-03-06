package com.softwarecraftsmen.dns;

import com.softwarecraftsmen.toString.ToString;
import com.softwarecraftsmen.unsignedIntegers.Unsigned32BitInteger;
import com.softwarecraftsmen.dns.messaging.serializer.AtomicWriter;
import com.softwarecraftsmen.dns.messaging.serializer.Serializable;
import com.softwarecraftsmen.dns.names.HostName;
import com.softwarecraftsmen.dns.MailBox;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StatementOfAuthority implements Serializable
{
	private final HostName primaryNameServerHostName;
	private final MailBox administratorMailbox;
	private final Unsigned32BitInteger serial;
	private final Seconds referesh;
	private final Seconds retry;
	private final Seconds expire;

	// TODO: Subclass / create MailBox which uses Name
	public StatementOfAuthority(final @NotNull HostName primaryNameServerHostName, final @NotNull MailBox administratorMailbox, final @NotNull Unsigned32BitInteger serial, final @NotNull Seconds refresh, final @NotNull Seconds retry, final @NotNull Seconds expire)
	{
		this.primaryNameServerHostName = primaryNameServerHostName;
		this.administratorMailbox = administratorMailbox;
		this.serial = serial;
		this.referesh = refresh;
		this.retry = retry;
		this.expire = expire;
	}

	@NotNull
	public String toString()
	{
		return ToString.string(this, primaryNameServerHostName, administratorMailbox, serial, referesh, retry, expire);
	}

	@SuppressWarnings({"RedundantIfStatement"})
	public boolean equals(final @Nullable Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}

		final StatementOfAuthority that = (StatementOfAuthority) o;

		if (!administratorMailbox.equals(that.administratorMailbox))
		{
			return false;
		}
		if (!expire.equals(that.expire))
		{
			return false;
		}
		if (!primaryNameServerHostName.equals(that.primaryNameServerHostName))
		{
			return false;
		}
		if (!referesh.equals(that.referesh))
		{
			return false;
		}
		if (!retry.equals(that.retry))
		{
			return false;
		}
		if (!serial.equals(that.serial))
		{
			return false;
		}
		return true;
	}

	public int hashCode()
	{
		int result;
		result = primaryNameServerHostName.hashCode();
		result = 31 * result + administratorMailbox.hashCode();
		result = 31 * result + serial.hashCode();
		result = 31 * result + referesh.hashCode();
		result = 31 * result + retry.hashCode();
		result = 31 * result + expire.hashCode();
		return result;
	}

	public void serialize(final @NotNull AtomicWriter writer)
	{
		primaryNameServerHostName.serialize(writer);
		administratorMailbox.serialize(writer);
		writer.writeUnsigned32BitInteger(serial);
		writer.writeUnsignedSeconds(referesh);
		writer.writeUnsignedSeconds(retry);
		writer.writeUnsignedSeconds(expire);
	}
}
